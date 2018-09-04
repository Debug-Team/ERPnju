package main.Data.CommodityData;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import main.Data.hibernateHelper.HibernateHelper;
import main.DataService.CommodityDataService.GoodsDataService;
import main.PO.GoodsPO;
import main.PO.ReciptGoodsPO;
import main.utility.ResultMessage;

@SuppressWarnings("deprecation")
public class GoodsData implements GoodsDataService{

	public GoodsData() {
	}
	
	@Override
	public boolean add(GoodsPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.save(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return true;
	}

	@Override
	public boolean delete(GoodsPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.delete(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return true;
	}

	@Override
	public boolean modify(GoodsPO po) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			if(po.getAmounts()<po.getAlertAmounts())
				try {
					sentAlertMessage(po);
				} catch (ClientException e) {
					e.printStackTrace();
				}
			session.update(po);
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ArrayList<GoodsPO> find(String keyword, String type) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<GoodsPO> goods = null;
		String hql = "from GoodsPO where " + type + " like '%" + keyword + "%'";
		
		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			List<GoodsPO> results = query.list();
			goods = new ArrayList<>(results);
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return goods;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultMessage updateCommodity(ArrayList<ReciptGoodsPO> list, String type, String lebal) {
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		ArrayList<GoodsPO> goods = null;
		
		try {
			tx = session.beginTransaction();

			for(int i=0; i<list.size(); i++){
				ReciptGoodsPO po = list.get(i);
				String hql = "from GoodsPO where " + "id" + " like '%" + po.getGoodsID() + "%'";
				Query query = session.createQuery(hql);
				List<GoodsPO> results = query.list();
				goods = new ArrayList<>(results);
				if(!goods.isEmpty()){
					GoodsPO goodspo = goods.get(0);
					int tmp = (int) (goodspo.getAmounts()-po.getAmounts());
					
					if(type.equals("JHTHD")&&lebal.equals("Unchecked")){
						
					}
					else if(type.equals("XSD")&&lebal.equals("Unchecked")){
						
					}
					else if(type.equals("JHD")&&lebal.equals("Checked")){
						goodspo.setAmounts(goodspo.getAmounts()+po.getAmounts());
						goodspo.setRecentBid(po.getBid());
						double avgValue = (goodspo.getAvgValue()*goodspo.getAmounts()+po.getBid()*po.getAmounts()) / (goodspo.getAmounts()+po.getAmounts());
						goodspo.setAvgValue(avgValue);
					}
					else if(type.equals("XSTHD")&&lebal.equals("Checked")){
						goodspo.setAmounts(goodspo.getAmounts()+po.getAmounts());
					}
					else if(type.equals("XSD")&&lebal.equals("Checked")){
						goodspo.setAmounts(tmp>0 ? tmp : 0);
						goodspo.setRecentBid(po.getBid());
					}
					else if(type.equals("JHTHD")&&lebal.equals("Checked")){
						goodspo.setAmounts(tmp>0 ? tmp : 0);
						double avgValue = (goodspo.getAvgValue()*goodspo.getAmounts()+po.getBid()*po.getAmounts()) / (goodspo.getAmounts()+po.getAmounts());
						goodspo.setAvgValue(avgValue);
					}

					modify(goodspo);
				}
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return ResultMessage.SUCCESS;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public int newID(String string){
		Session session = HibernateHelper.getSession();
		Transaction tx = null;
		int ret = 0;
		String hql = "from GoodsPO where catagory ='" + string +"'";
		GoodsPO po = null;
		
		try {
			tx = session.beginTransaction();
			
			Query query = session.createQuery(hql);
			List<GoodsPO> results = query.list();
			if(!results.isEmpty()){
				po = results.get(results.size()-1);
				String s = po.getID().substring(7);
				ret = Integer.parseInt(s);
			}else{
				ret = 0;
			}
			
			tx.commit();
		} catch (HibernateException e) {
			if(tx != null) 
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return ret+1;
	}
	
	@Override
	public void sentAlertMessage(GoodsPO po) throws ClientException{
		//设置超时时间-可自行调整
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		//初始化ascClient需要的几个参数
		final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
		final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
		//替换成你的AK
		final String accessKeyId = "LTAIQjJDgdUWe46y";//你的accessKeyId,参考本文档步骤2
		final String accessKeySecret = "UsqadsMvBRfeBPzgdsocmGEOhIf3sN";//你的accessKeySecret，参考本文档步骤2
		final String phoneNum = "18795957252";
		//初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
		accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		 //组装请求对象
		 SendSmsRequest request = new SendSmsRequest();
		 //使用post提交
		 request.setMethod(MethodType.POST);
		 //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		 request.setPhoneNumbers(phoneNum);
		 //必填:短信签名-可在短信控制台中找到
		 request.setSignName("灯具进销存系统");
		 //必填:短信模板-可在短信控制台中找到
		 request.setTemplateCode("SMS_119925785");
		 //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		 //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		 request.setTemplateParam("{\"gName\":\""+po.getName()+"\", \"gID\":\""+po.getID()+"\", \"num\":\""+po.getAmounts()+"\", \"alertNum\":\""+po.getAlertAmounts()+"\"}");
		 //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		 //request.setSmsUpExtendCode("90997");
		 //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		 request.setOutId("yourOutId");
		//请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			System.out.println("短信通知成功");
		}
	}
}
