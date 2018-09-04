package main.BussinessLogic.RecordBL;

import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

import main.BussinessLogic.CommodityBL.CommodityBL;
import main.BussinessLogic.CommodityBL.CommodityService;
import main.BussinessLogic.CustomerBL.CustomerBL;
import main.BussinessLogic.CustomerBL.CustomerService;
import main.BussinessLogic.ManifestBL.ManifestBL;
import main.BussinessLogic.ManifestBL.ManifestService;
import main.BussinessLogic.ReciptBL.ReciptBL;
import main.BussinessLogic.ReciptBL.ReciptService;
import main.PO.GoodsPO;
import main.VO.CashBillVO;
import main.VO.CollectionOrderVO;
import main.VO.CommodityReciptVO;
import main.VO.CustomerVO;
import main.VO.GoodsVO;
import main.VO.InfoVO;
import main.VO.ManifestVO;
import main.VO.PaymentOrderVO;
import main.VO.ReciptGoodsVO;
import main.VO.SaleStateVO;
import main.utility.DocumentsType;
import main.utility.ResultMessage;

public class RecordBL {

	public ArrayList<ReciptGoodsVO> getSalesDetailList(String startTime, String endTime, 
			String goodsName, String customerName, String operator) {
		ManifestService ms = new ManifestBL();
		return ms.getSalesDetailList(startTime, endTime, goodsName, customerName, operator);
	}
	
	public InfoVO getSalesHistoryList(String startTime, String endTime, 
			DocumentsType type, String customerName, String operator) {
		InfoVO result = new InfoVO();
		if(type == DocumentsType.GOODSIN_DOCUMENTS) {
			ManifestService ms = new ManifestBL();
			ArrayList<ManifestVO> documents = ms.getSalesHistoryList(startTime, endTime, customerName, operator, type);
			for(int i = 0; i < documents.size(); i++) {
				if(documents.get(i).getType().equals("XSD") && documents.get(i).getType().equals("XSTHD")) {
					documents.remove(documents.get(i));
					i--;
				}
			}
			result.setManifestList(documents);
		}
		else if(type == DocumentsType.SALES_DOCUMENTS) {
			ManifestService ms = new ManifestBL();
			ArrayList<ManifestVO> documents = ms.getSalesHistoryList(startTime, endTime, customerName, operator, type);
			for(int i = 0; i < documents.size(); i++) {
				if(documents.get(i).getType().equals("JHTHD") && documents.get(i).getType().equals("JHD")) {
					documents.remove(documents.get(i));
					i--;
				}
			}
			result.setManifestList(documents);
		}
		else if(type == DocumentsType.INVENTORY_DOCUMENTS) {
			CommodityService cs = new CommodityBL();
			ArrayList<CommodityReciptVO> documents = cs.getCheckedCommodityRecipt(startTime, endTime, customerName, operator);
			result.setCommodityReciptList(documents);
		}
		else if(type == DocumentsType.FINANCIAL_DOCUMENTS) {
			ReciptService rs = new ReciptBL();
			ArrayList<CollectionOrderVO> collectionOrderList = rs.getCheckedCollectionOrderList(startTime, endTime, customerName, operator);
			ArrayList<PaymentOrderVO> paymentOrderList = rs.getCheckedPaymentOrderList(startTime, endTime, customerName, operator);
			ArrayList<CashBillVO> cashBillList = rs.getCheckedCashBillList(startTime, endTime, customerName, operator);
			result.setCollectionOrderList(collectionOrderList);
			result.setPaymentOrderList(paymentOrderList);
			result.setCashBillList(cashBillList);
		}
		else {
			ManifestService ms = new ManifestBL();
			ArrayList<ManifestVO> ManifestVO_documents1 = ms.getSalesHistoryList(startTime, endTime, customerName, operator, DocumentsType.GOODSIN_DOCUMENTS);
			ArrayList<ManifestVO> ManifestVO_documents2 = ms.getSalesHistoryList(startTime, endTime, customerName, operator, DocumentsType.SALES_DOCUMENTS);
			ManifestVO_documents1.addAll(ManifestVO_documents2);
			result.setManifestList(ManifestVO_documents1);
			CommodityService cs = new CommodityBL();
			ArrayList<CommodityReciptVO> CommodityReciptVO_documents = cs.getCheckedCommodityRecipt(startTime, endTime, customerName, operator);
			result.setCommodityReciptList(CommodityReciptVO_documents);
			ReciptService rs = new ReciptBL();
			ArrayList<CollectionOrderVO> collectionOrderList = rs.getCheckedCollectionOrderList(startTime, endTime, customerName, operator);
			ArrayList<PaymentOrderVO> paymentOrderList = rs.getCheckedPaymentOrderList(startTime, endTime, customerName, operator);
			ArrayList<CashBillVO> cashBillList = rs.getCheckedCashBillList(startTime, endTime, customerName, operator);
			result.setCollectionOrderList(collectionOrderList);
			result.setPaymentOrderList(paymentOrderList);
			result.setCashBillList(cashBillList);
		}
		return result;
	}
	
	public SaleStateVO getSalesStateList(String startTime, String endTime) {
		InfoVO vo = getSalesHistoryList(startTime, endTime, DocumentsType.ALL, "", "");
		//获取各种单据
		ArrayList<ManifestVO> manifestList = vo.getManifestList();
		ArrayList<CommodityReciptVO> CommodityReciptList = vo.getCommodityReciptList();
		ArrayList<CollectionOrderVO> collectionOrderList = vo.getCollectionOrderList();
		//计算销售成本
		double saleCost = 0;
		for(int i = 0; i < manifestList.size(); i++) {
			String type = manifestList.get(i).getType();
			if(type.equals("JHD")) {
				saleCost += manifestList.get(i).getSum();
			}
		}
		CommodityService cs = new CommodityBL();
		//计算商品收入
		double goodsIncome = 0;
		for(int i = 0; i < CommodityReciptList.size(); i++) {
			String type = CommodityReciptList.get(i).getType();
			if(type.equals("BY")) {
				goodsIncome += cs.findGoodsByID(CommodityReciptList.get(i).getGoodsID()).getBid();
			}
		}
		//计算商品支出
		double goodsCost = 0;
		for(int i = 0; i < CommodityReciptList.size(); i++) {
			goodsCost += cs.findGoodsByID(CommodityReciptList.get(i).getGoodsID()).getBid() * CommodityReciptList.get(i).getChangedNumbers();
		}
		//计算商品折让
		double goodsDiscount = 0;
		for(int i = 0; i < manifestList.size(); i++) {
			String type = manifestList.get(i).getType();
			if(type.equals("XSD")) {
				saleCost += manifestList.get(i).getPromotionDiscount();
			}
		}
		//计算销售收入
		double saleIncome = 0;
		for(int i = 0; i < collectionOrderList.size(); i++) {
			saleIncome += collectionOrderList.get(i).getSum();
		}
		
		return new SaleStateVO(saleIncome, goodsIncome, saleCost, goodsCost, goodsDiscount);
	}
	
	public ArrayList<GoodsVO> getGoods(String condition, String part) throws RemoteException {
		CommodityService cs = new CommodityBL();
		ArrayList<GoodsPO> goodsList = cs.getGoodsList(part, condition);
		ArrayList<GoodsVO> result = new ArrayList<GoodsVO>();
		for(int i = 0; i < goodsList.size(); i++) {
			GoodsVO vo = new GoodsVO(goodsList.get(i));
			result.add(vo);
		}
		return result;
	}

	public ArrayList<CustomerVO> getCustomer(String condition, String part) throws RemoteException {
		CustomerService cs = new CustomerBL();
		return cs.customerFind(part, condition);
	}
	
	public boolean setRedDashed(String type, String id, String operator) {
		if(type.equals("进货单") || type.equals("销售单") || type.equals("进货退货单") || type.equals("销售退货单")) {
			if(type.equals("进货单")) { 
				type = "JHD";
			}
			else if(type.equals("销售单")) { 
				type = "XSD";
			}
			else if(type.equals("进货退货单")) { 
				type = "JHTHD";
			}
			else if(type.equals("销售退货单")) { 
				type = "XSTHD";
			}
			ManifestService ms = new ManifestBL();
			return ms.setRedDashed(type, id);
		}
		else if(type.equals("现金费用单") || type.equals("收款单") || type.equals("付款单")) {
			if(type.equals("现金费用单")) {
				type = "XJFYD";
			}
			else if(type.equals("收款单")) {
				type = "SKD";
			}
			else if(type.equals("付款单")) {
				type = "FKD";
			}
			ReciptService rs = new ReciptBL();
			return rs.setRedDashed(type, id);
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public ResultMessage saleStateToExcel(SaleStateVO vo, String fileName, String storeAdress) {
		// 第一步，创建一个webbook，对应一个Excel文件    
        HSSFWorkbook wb = new HSSFWorkbook();    
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet    
        HSSFSheet sheet = wb.createSheet("销售状态表");    
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short    
        HSSFRow row = sheet.createRow(0);    
        
        // 单元格样式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        HSSFCell cell = row.createCell((short) 0);    
        cell.setCellValue("销售收入");    
        cell.setCellStyle(cellStyle);    
        cell = row.createCell((short) 1);    
        cell.setCellValue("商品收入");    
        cell.setCellStyle(cellStyle);    
        cell = row.createCell((short) 2);    
        cell.setCellValue("销售成本");    
        cell.setCellStyle(cellStyle);    
        cell = row.createCell((short) 3);    
        cell.setCellValue("商品支出");    
        cell.setCellStyle(cellStyle);
        cell = row.createCell((short) 4);    
        cell.setCellValue("商品折让");    
        cell.setCellStyle(cellStyle);
        cell = row.createCell((short) 5);    
        cell.setCellValue("总利润");    
        cell.setCellStyle(cellStyle);
    
        // 第五步，写入实体数据       
        row = sheet.createRow(1);  
        //创建单元格  
        row.createCell(0).setCellValue(vo.getSaleIncome()); 
        row.getCell(0).setCellStyle(cellStyle);
        row.createCell(1).setCellValue(vo.getGoodsIncome());
        row.getCell(1).setCellStyle(cellStyle);
        row.createCell(2).setCellValue(vo.getSaleCost()); 
        row.getCell(2).setCellStyle(cellStyle);
        row.createCell(3).setCellValue(vo.getGoodsCost());
        row.getCell(3).setCellStyle(cellStyle);
        row.createCell(4).setCellValue(vo.getGoodsDiscount());
        row.getCell(4).setCellStyle(cellStyle);
        row.createCell(5).setCellValue(vo.getTotal());
        row.getCell(5).setCellStyle(cellStyle);
        // 第六步，将文件存到指定位置    
        try {    
            FileOutputStream fout = new FileOutputStream(storeAdress+"\\"+fileName);    
            wb.write(fout);    
            fout.close();  
            wb.close();
        }    
        catch (Exception e) {    
            e.printStackTrace();    
        }  
	
		return ResultMessage.SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	public ResultMessage salesHistoryToExcel(InfoVO info, String fileName, String storeAdress) {
		// 第一步，创建一个webbook，对应一个Excel文件    
        HSSFWorkbook wb = new HSSFWorkbook();    
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet    
        HSSFSheet sheet = wb.createSheet("销售明细表");    
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short    
        HSSFRow row = sheet.createRow(0);    
        
        // 单元格样式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        HSSFCell cell = row.createCell((short) 0);    
        cell.setCellValue("类型");    
        cell.setCellStyle(cellStyle);    
        cell = row.createCell((short) 1);    
        cell.setCellValue("编号");    
        cell.setCellStyle(cellStyle);    
        cell = row.createCell((short) 2);    
        cell.setCellValue("操作员");    
        cell.setCellStyle(cellStyle);    
        cell = row.createCell((short) 3);    
        cell.setCellValue("日期");

        // 第五步，写入实体数据       
        ArrayList<ManifestVO> manifestList = info.getManifestList();
        ArrayList<CommodityReciptVO> commodityReciptList = info.getCommodityReciptList();
        ArrayList<CollectionOrderVO> collectionOrderList = info.getCollectionOrderList();
        ArrayList<PaymentOrderVO> paymentOrderList = info.getPaymentOrderList();
        ArrayList<CashBillVO> cashBillList = info.getCashBillList();
        
        for (int i = 0; i < manifestList.size(); i++) {   
        	ManifestVO vo = manifestList.get(i);
        	String voType = vo.getType();
    		switch(voType) {
	    		case "XSD":{
	    			voType = "销售单";
	    			break;
	    		}
	    		case "XSTHD":{
	    			voType = "销售退货单";
	    			break;
	    		}
	    		case "JHD":{
	    			voType = "进货单";
	    			break;
	    		}
	    		case "JHTHD":{
	    			voType = "进货退货单";
	    			break;
	    		}
    		}
            row = sheet.createRow((int) i + 1);  
            //创建单元格  
            row.createCell(0).setCellValue(voType); 
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1).setCellValue(vo.getID());
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2).setCellValue(vo.getOperator()); 
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellValue(vo.getCreateDate());
            row.getCell(3).setCellStyle(cellStyle);
        }    
    		
		for (int i = 0; i < commodityReciptList.size(); i++) {   
			CommodityReciptVO vo = commodityReciptList.get(i);
			String voType = vo.getType();
			switch(voType) {
				case "BJ":{
					voType = "库存报警单";
					break;
				}
				case "ZS":{
					voType = "库存赠送单";
					break;
				}
				case "BY":{
					voType = "库存报溢单";
					break;
				}
				case "BS":{
					voType = "库存报损单";
					break;
				}
			}

            row = sheet.createRow((int) i + 1);  
            //创建单元格  
            row.createCell(0).setCellValue(voType); 
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1).setCellValue(vo.getID());
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2).setCellValue(vo.getStaffID()); 
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellValue(vo.getCreateDate());
            row.getCell(3).setCellStyle(cellStyle);
        }   
		for(int i = 0; i < collectionOrderList.size(); i++) {
			CollectionOrderVO vo = collectionOrderList.get(i);
			 row = sheet.createRow((int) i + 1);  
            //创建单元格  
            row.createCell(0).setCellValue("收款单"); 
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1).setCellValue(vo.getId());
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2).setCellValue(vo.getOperator()); 
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellValue(vo.getId().split("-")[1]);
            row.getCell(3).setCellStyle(cellStyle);
		}
		for(int i = 0; i < paymentOrderList.size(); i++) {
			PaymentOrderVO vo = paymentOrderList.get(i);
			row = sheet.createRow((int) i + 1);  
	       //创建单元格  
	       row.createCell(0).setCellValue("付款单"); 
	       row.getCell(0).setCellStyle(cellStyle);
	       row.createCell(1).setCellValue(vo.getId());
	       row.getCell(1).setCellStyle(cellStyle);
	       row.createCell(2).setCellValue(vo.getOperator()); 
	       row.getCell(2).setCellStyle(cellStyle);
	       row.createCell(3).setCellValue(vo.getId().split("-")[1]);
	       row.getCell(3).setCellStyle(cellStyle);
		}
		for(int i = 0; i < cashBillList.size(); i++) {
			CashBillVO vo = cashBillList.get(i);
			row = sheet.createRow((int) i + 1);  
	       //创建单元格  
	       row.createCell(0).setCellValue("现金费用单"); 
	       row.getCell(0).setCellStyle(cellStyle);
	       row.createCell(1).setCellValue(vo.getId());
	       row.getCell(1).setCellStyle(cellStyle);
	       row.createCell(2).setCellValue(vo.getOperator()); 
	       row.getCell(2).setCellStyle(cellStyle);
	       row.createCell(3).setCellValue(vo.getId().split("-")[1]);
	       row.getCell(3).setCellStyle(cellStyle);
		}
        // 第六步，将文件存到指定位置    
        try {    
            FileOutputStream fout = new FileOutputStream(storeAdress+"\\"+fileName);    
            wb.write(fout);    
            fout.close();  
            wb.close();
        }    
        catch (Exception e) {    
            e.printStackTrace();    
        }  
	
		return ResultMessage.SUCCESS;
	}
	
	@SuppressWarnings("deprecation")
	public ResultMessage salesDetailToExcel(ArrayList<ReciptGoodsVO> voList, String fileName, String storeAdress) {
		// 第一步，创建一个webbook，对应一个Excel文件    
        HSSFWorkbook wb = new HSSFWorkbook();    
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet    
        HSSFSheet sheet = wb.createSheet("销售明细表");    
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short    
        HSSFRow row = sheet.createRow(0);    
        
        // 单元格样式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        HSSFCell cell = row.createCell((short) 0);    
        cell.setCellValue("时间");    
        cell.setCellStyle(cellStyle);    
        cell = row.createCell((short) 1);    
        cell.setCellValue("商品名");    
        cell.setCellStyle(cellStyle);    
        cell = row.createCell((short) 2);    
        cell.setCellValue("型号");    
        cell.setCellStyle(cellStyle);    
        cell = row.createCell((short) 3);    
        cell.setCellValue("数量");    
        cell.setCellStyle(cellStyle);
        cell = row.createCell((short) 4);    
        cell.setCellValue("单价");    
        cell.setCellStyle(cellStyle);
        cell = row.createCell((short) 5);    
        cell.setCellValue("总价");    
        cell.setCellStyle(cellStyle);
    
        // 第五步，写入实体数据       
        for (int i = 0; i < voList.size(); i++) {   
        	ReciptGoodsVO vo = voList.get(i);
            row = sheet.createRow((int) i + 1);  
            //创建单元格  
            row.createCell(0).setCellValue(vo.getComment()); 
            row.getCell(0).setCellStyle(cellStyle);
            row.createCell(1).setCellValue(vo.getName());
            row.getCell(1).setCellStyle(cellStyle);
            row.createCell(2).setCellValue(vo.getVersion()); 
            row.getCell(2).setCellStyle(cellStyle);
            row.createCell(3).setCellValue(vo.getAmounts());
            row.getCell(3).setCellStyle(cellStyle);
            row.createCell(4).setCellValue(vo.getBid());
            row.getCell(4).setCellStyle(cellStyle);
            row.createCell(5).setCellValue(vo.getSum());
            row.getCell(5).setCellStyle(cellStyle);
        }    
        // 第六步，将文件存到指定位置    
        try {    
            FileOutputStream fout = new FileOutputStream(storeAdress+"\\"+fileName);    
            wb.write(fout);    
            fout.close();  
            wb.close();
        }    
        catch (Exception e) {    
            e.printStackTrace();    
        }  
	
		return ResultMessage.SUCCESS;
	}
	
}
