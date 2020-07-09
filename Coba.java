package com.me.vd.main;
import java.io.BufferedReader;


import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.mortbay.jetty.HttpHeaders;
import org.mortbay.jetty.MimeTypes;

import com.me.vd.vd.VD;


public class Coba
{
    static HostnameVerifier hv = new HostnameVerifier()
    {
        public boolean verify(String urlHostName, SSLSession session)
        {
            System.out.println("The verify method simply return 'true'");
            return true;
        }
    };
    
    public static void main(String[] args)
    {
    	HitPPOB_XML();
 //   	HitAlfamartPPOB();
//    	HitIndomaretPPOB();
//    	HitZonatik();
//    	Test_CMS();
    }

  	private static void Test_CMS()
  	{
  		try {
  			SendTelkomselPostPaid();
  			SendBPJS();
  		} catch (Exception e) {
  			System.out.println("ERROR -> Test_CMS\n" + e);
  		}
  	}
    
  	private static String SendTelkomselPostPaid()
	{
  		String sRoot 			= "", sTrxId 			= "", sDate			= "";
		String sBankID 			= "0928";
		String sMsisdn 			= "081123456701";
		String sTotalAmount		= "0";
		
		String sCustNo			= "0";
		String sAmount			= "217399";
		String sDueDate			= "MAR17";
		
		int iType 				= 3;
		if (iType == 1){
			sRoot 	= "request_inquiry";
			sDate 	= new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			sTrxId 	= getNewTrxID();
		}
		else if (iType == 2){
			sRoot 	= "request_payment";
			sDate 	= new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			sTrxId 	= getNewTrxID();
		}
		else if (iType == 3){
			sRoot 	= "request_reversal";
			sTrxId 	= "173K94441001";
			sDate	= "20170320094441";
		}

		StringBuffer sbTopup = new StringBuffer();
		sbTopup.append("type=xml&data=");
		sbTopup.append("<" + sRoot + ">");
		sbTopup.append("<bank_id>" + sBankID + "</bank_id>");
		sbTopup.append("<merch_type>6012</merch_type>");
		sbTopup.append("<terminal_id>-</terminal_id>");
		sbTopup.append("<date_time>" + sDate + "</date_time>");
		sbTopup.append("<inst_code>1016</inst_code>");
		sbTopup.append("<stan>015914</stan>");
		sbTopup.append("<trx_id>" + sTrxId + "</trx_id>");
		sbTopup.append("<url>http%3A%2F%2F0.0.0.0%3A12345/?biller=TELKOMSEL_POST</url>");
		sbTopup.append("<data>");
		sbTopup.append("  <msisdn>" + sMsisdn + "</msisdn>");
		if (iType == 2 || iType == 3){
			sbTopup.append("  <cust_name>" + sTotalAmount + "</cust_name>");
			sbTopup.append("  <cust_no>" + sCustNo + "</cust_no>");
			sbTopup.append("  <amount>" + sAmount + "</amount>");
			sbTopup.append("  <due_date>" + sDueDate + "</due_date>");
		}
		sbTopup.append("</data>");
		sbTopup.append("</" + sRoot + ">");
	   	 
		String sXML = sbTopup.toString();
		String sResponse = sendHTTPURLConnPOST("http://127.0.0.1:10016/?biller=TELCO_TELKOMSEL_POST",sXML);
		return "";
	}
  	
  	private static String SendBPJS()
	{
  		String sRoot 			= "", sTrxId 			= "", sDate			= "";
		String sBankID 			= "0928";
		String sMsisdn 			= "081123456701";
		String sTotalAmount		= "0";
		
		String sCustNo			= "0";
		String sAmount			= "217399";
		String sDueDate			= "MAR17";
		
		int iType 				= 3;
		if (iType == 1){
			sRoot 	= "request_inquiry";
			sDate 	= new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			sTrxId 	= getNewTrxID();
		}
		else if (iType == 2){
			sRoot 	= "request_payment";
			sDate 	= new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			sTrxId 	= getNewTrxID();
		}
		else if (iType == 3){
			sRoot 	= "request_reversal";
			sTrxId 	= "173K94441001";
			sDate	= "20170320094441";
		}

		StringBuffer sbTopup = new StringBuffer();
		sbTopup.append("type=xml&data=");
		sbTopup.append("<" + sRoot + ">");
		sbTopup.append("<bank_id>" + sBankID + "</bank_id>");
		sbTopup.append("<merch_type>6012</merch_type>");
		sbTopup.append("<terminal_id>-</terminal_id>");
		sbTopup.append("<date_time>" + sDate + "</date_time>");
		sbTopup.append("<inst_code>1016</inst_code>");
		sbTopup.append("<stan>015914</stan>");
		sbTopup.append("<trx_id>" + sTrxId + "</trx_id>");
		sbTopup.append("<url>http%3A%2F%2F0.0.0.0%3A12345/?biller=TELKOMSEL_POST</url>");
		sbTopup.append("<data>");
		sbTopup.append("  <msisdn>" + sMsisdn + "</msisdn>");
		if (iType == 2 || iType == 3){
			sbTopup.append("  <cust_name>" + sTotalAmount + "</cust_name>");
			sbTopup.append("  <cust_no>" + sCustNo + "</cust_no>");
			sbTopup.append("  <amount>" + sAmount + "</amount>");
			sbTopup.append("  <due_date>" + sDueDate + "</due_date>");
		}
		sbTopup.append("</data>");
		sbTopup.append("</" + sRoot + ">");
	   	 
		String sXML = sbTopup.toString();
		String sResponse = sendHTTPURLConnPOST("http://127.0.0.1:10016/?biller=TELCO_TELKOMSEL_POST",sXML);
		return "";
	}
  	
    private static void HitAlfamartPPOB()
    {
        	try
        	{
        		String sDateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        		String sURL	= "", sData = "";
        		
        		String sAgentID = "alfamart";
        		String sAgentPIN = "p94RvjWx";
        		String sAgentstoreID = "K100";
//        		String sProductID = "3001";	//TOPASTV
//        		String sProductID = "4001";	//KSP_NASARI
//        		String sProductID = "4003";	//MAS_FINANCE
//        		String sProductID = "2003";	//PAM_ATB (BATAM)
        		String sProductID = "6001";	//MAS_FINANCE
        		
//        		String sCustomerID = "10100170000082";	//MAS FINANCE
//        		String sCustomerID = "10100170000075";	//MAS FINANCE
//        		String sCustomerID = "99112";	//PAM ATB Batam
        		String sCustomerID = "317800800300700771-2016";	//MAS FINANCE
        		
        		String sSecretKey = "6iM48T7Ra206Mm";
        		String sPaymentPeriod = "", sAmount = "", sCharge = "", sTotal = "", sAdminFee = "";
        		
        		String sTrxID = getNewTrxID();
        		
        		int iType = 2;

    //Inquiry
        		if (iType == 1)
        		{
    	    		sURL = "http://127.0.0.1:10002/InquiryData?";	//ALFAMART PPOB
//    	    		String sURL = "http://202.169.43.53:52132/InquiryData?";	//ALFAMART PPOB
    	    		
    	    		String sData_Signature = sAgentID + sAgentPIN + sTrxID + sAgentstoreID + 
    						sProductID + sCustomerID + sDateTime + sSecretKey;
    	    		System.out.println("Data Signature : [" + sData_Signature + "]");
    	    		String sSignatureSHA1 = getSHA1_32BIT(sData_Signature);
    	    		sData =	"AgentID=" + URLEncoder.encode(sAgentID,"UTF-8") +
    	    				"&AgentPIN=" + URLEncoder.encode(sAgentPIN,"UTF-8") +
    	    				"&AgentTrxID=" + URLEncoder.encode(sTrxID,"UTF-8") +
    	    				"&AgentStoreID=" + URLEncoder.encode(sAgentstoreID,"UTF-8") +
    	    				"&ProductID=" + URLEncoder.encode(sProductID,"UTF-8") +
    	    				"&CustomerID=" + URLEncoder.encode(sCustomerID,"UTF-8") +
    	    				"&DateTimeRequest=" + URLEncoder.encode(sDateTime,"UTF-8") + 
    	    				"&Signature=" + URLEncoder.encode(sSignatureSHA1,"UTF-8");
        		}
    //Payment
        		else if (iType == 2)
        		{
            		sPaymentPeriod	= "1,2,3";	//MAS FINANCE
//            		sPaymentPeriod	= "201801";
            		sAmount 		= "51000";
            		sCharge 		= "0";
            		sTotal 			= "51000";
            		sAdminFee 		= "0";
        			
    	    		sURL = "http://127.0.0.1:10002/Payment?";	//ALFAMART PPOB
    	    		String sData_Signature = sAgentID + sAgentPIN + sTrxID + sAgentstoreID + 
    	    								sProductID + sCustomerID + sDateTime + sPaymentPeriod + sAmount + 
    	    								sCharge + sTotal + sAdminFee + sSecretKey;
    	    		System.out.println("Data Signature : [" + sData_Signature + "]");
    	    		String sSignatureSHA1 = getSHA1_32BIT(sData_Signature);
    	    		
    	    		sData = "AgentID=" + URLEncoder.encode(sAgentID,"UTF-8")  +
    	    				"&AgentPIN=" + URLEncoder.encode(sAgentPIN,"UTF-8") +
    	    				"&AgentTrxID=" + URLEncoder.encode(sTrxID,"UTF-8") +
    	    				"&AgentStoreID=" + URLEncoder.encode(sAgentstoreID,"UTF-8") +
    	    				"&ProductID=" + URLEncoder.encode(sProductID,"UTF-8") +
    	        			"&CustomerID=" + URLEncoder.encode(sCustomerID,"UTF-8") +
    	        			"&DateTimeRequest=" + URLEncoder.encode(sDateTime,"UTF-8") +
    	        			"&PaymentPeriod=" + URLEncoder.encode(sPaymentPeriod,"UTF-8") +
    	        			"&Amount=" + URLEncoder.encode(sAmount,"UTF-8") +
    	        			"&Charge=" + URLEncoder.encode(sCharge,"UTF-8") +
    	        			"&Total=" + URLEncoder.encode(sTotal,"UTF-8") +
    	        			"&AdminFee=" + URLEncoder.encode(sAdminFee,"UTF-8") +
    	        			"&Signature=" + URLEncoder.encode(sSignatureSHA1,"UTF-8");
        		}
    //Reversal
        		else if (iType == 3)
        		{
    	    		sURL = "http://127.0.0.1:10002/Reversal?";	//ALFAMART PPOB
    	    		sTrxID = "1831D5622001";
    	    		sDateTime = "20180301135624";
    	    		String sData_Signature = sAgentID + sAgentPIN + sTrxID + sAgentstoreID + 
    	    								sProductID + sCustomerID + sDateTime + sSecretKey;
    	    		System.out.println("Data Signature : [" + sData_Signature + "]");
    	    		String sSignatureSHA1 = getSHA1_32BIT(sData_Signature);
    	    		
    	    		sData =	"AgentID=" + URLEncoder.encode(sAgentID,"UTF-8") +
    	    				"&AgentPIN=" + URLEncoder.encode(sAgentPIN,"UTF-8") +
    	    				"&AgentTrxID=" + URLEncoder.encode(sTrxID,"UTF-8") +
    	    				"&AgentStoreID=" + URLEncoder.encode(sAgentstoreID,"UTF-8") +
    	    				"&ProductID=" + URLEncoder.encode(sProductID,"UTF-8") +
    	    				"&CustomerID=" + URLEncoder.encode(sCustomerID,"UTF-8") +
    	    				"&DateTimeRequest=" + URLEncoder.encode(sDateTime,"UTF-8") + 
    	    				"&Signature=" + URLEncoder.encode(sSignatureSHA1,"UTF-8");
        		}
    //Inquiry Status
        		else if (iType == 4)
        		{
    	    		sURL = "http://127.0.0.1:10002/InquiryStatus?";	//ALFAMART PPOB
    				sTrxID = "182RD3131001";
    				String sData_Signature = sAgentID + sAgentPIN + sTrxID + sAgentstoreID + 
    										sProductID + sCustomerID + sDateTime + sSecretKey;
    				System.out.println("Data Signature : [" + sData_Signature + "]");
    				String sSignatureSHA1 = getSHA1_32BIT(sData_Signature);
    				sData =	"AgentID=" + URLEncoder.encode(sAgentID,"UTF-8") +
    						"&AgentPIN=" + URLEncoder.encode(sAgentPIN,"UTF-8") +
    						"&AgentTrxID=" + URLEncoder.encode(sTrxID,"UTF-8") +
    						"&AgentStoreID=" + URLEncoder.encode(sAgentstoreID,"UTF-8") +
    						"&ProductID=" + URLEncoder.encode(sProductID,"UTF-8") +
    						"&CustomerID=" + URLEncoder.encode(sCustomerID,"UTF-8") +
    						"&DateTimeRequest=" + URLEncoder.encode(sDateTime,"UTF-8") + 
    						"&Signature=" + URLEncoder.encode(sSignatureSHA1,"UTF-8");
        		}
    //COMMIT
        		else if (iType == 5)
        		{
    	    		sURL = "http://127.0.0.1:10002/Commit?";	//ALFAMART PPOB
    				sTrxID = "182RD3131001";
//    				sCustomerID = "";
    				String sData_Signature = sAgentID + sAgentPIN + sTrxID + sAgentstoreID + 
    										sProductID + sCustomerID + sDateTime + sSecretKey;
    				System.out.println("Data Signature : [" + sData_Signature + "]");
    				String sSignatureSHA1 = getSHA1_32BIT(sData_Signature);
    				sData =	"AgentID=" + URLEncoder.encode(sAgentID,"UTF-8") +
    						"&AgentPIN=" + URLEncoder.encode(sAgentPIN,"UTF-8") +
    						"&AgentTrxID=" + URLEncoder.encode(sTrxID,"UTF-8") +
    						"&AgentStoreID=" + URLEncoder.encode(sAgentstoreID,"UTF-8") +
    						"&ProductID=" + URLEncoder.encode(sProductID,"UTF-8") +
    						"&CustomerID=" + URLEncoder.encode(sCustomerID,"UTF-8") +
    						"&DateTimeRequest=" + URLEncoder.encode(sDateTime,"UTF-8") + 
    						"&Signature=" + URLEncoder.encode(sSignatureSHA1,"UTF-8");
        		}
        		sendHTTPURLConnGET(sURL + sData);

//        		System.out.println("Kalimat Testing: [" + getSHA1_32BIT("kalimattesting") + "]");
        	}
        	catch (Exception e)
        	{
        		System.out.println("[HitAlfamartPPOB] Error Exception");
        		e.printStackTrace();
        	}
        
    }
    
    private static void HitIndomaretPPOB()
    {
    	try
    	{
//    		String sDateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    		String sTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss").format(new Date());
    		String sPaymentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss").format(new Date());

//    		String sProductID = "90701";
//    		String sCustomerID = "1600195";
    		
//    		String sProductID = "90702";	//KSP NASARI
//    		String sProductID = "90402";	//PBB BEKASI
    	//	String sProductID = "90026";	//Bolt Postpaid
    		//String sProductID = "90610";	//PAM Palyja
    		String sProductID = "90643";
//    		String sCustomerID = "00601300000349";
//    		String sCustomerID = "00601300000330";
//    		String sCustomerID = "3275070001010100802015";	//Nmr tes PBB Bekasi
//    		String sCustomerID = "163501033";
    		String sCustomerID = "11007336";
//Inquiry
    		String sURL = "http://127.0.0.1:10003/request";	//INDOMARET PPOB
    		String sTrxID = getNewTrxID();
    		String sData = 	"type=xml&data=<InquiryReq>"
	    				+ "<TimeStamp>" + sTimeStamp + "</TimeStamp>"
	    				+ "<MessageID>" + sTrxID + "</MessageID>"
	    				+ "<Ref1>" + sCustomerID + "</Ref1>"
	    				+ "<Ref2>" + sProductID + "</Ref2>"
	    				+ "<TrackingRef>157575795398</TrackingRef>"
	    				+ "<StoreID>G002T005</StoreID>"
	    				+ "</InquiryReq>";
    		
//Payment
//    		String sURL = "http://127.0.0.1:10003/request";	//INDOMARET PPOB
//    		String sTrxID = getNewTrxID();
//    		String sAmount = "67920000";
//    		String sData = 	"type=xml&data=<PaymentReq>"
//	    				+ "<TimeStamp>" + sTimeStamp + "</TimeStamp>"
//	    				+ "<MessageID>" + sTrxID + "</MessageID>"
//	    				+ "<Ref1>" + sCustomerID + "</Ref1>"
//	    				+ "<Ref2>" + sProductID + "</Ref2>"
//	    				+ "<Amount>" + sAmount + "</Amount>"
//	    				+ "<PaymentDateTime>" + sPaymentDateTime + "</PaymentDateTime>"
//	    				+ "<TrackingRef>157575795398</TrackingRef>"
//	    				+ "<StoreID>G002T005</StoreID>"
//	    				+ "</PaymentReq>";
    		
//Reversal
//    		String sURL = "http://127.0.0.1:10003/request";	//INDOMARET PPOB
//    		String sTrxID = "TAAI201612702100704";
//    		String sAmount = "25000000";
//    		String sData = 	"type=xml&data=<ReversalReq>"
//	    				+ "<TimeStamp>" + sTimeStamp + "</TimeStamp>"
//	    				+ "<MessageID>" + sTrxID + "</MessageID>"
//	    				+ "<Ref1>" + sCustomerID + "</Ref1>"
//	    				+ "<Ref2>" + sProductID + "</Ref2>"
//	    				+ "<Amount>" + sAmount + "</Amount>"
//	    				+ "<ReversalDateTime>2016-12-02 10:07:14</ReversalDateTime>"
//	    				+ "<TrackingRef>165657338771</TrackingRef>"
//	    				+ "<StoreID>G001TAAI</StoreID>"
//	    				+ "</ReversalReq>";
    		
//    		sendHTTPURLConnGET(sURL + sData);
    		sendHTTPURLConnPOST(sURL, sData);

//    		System.out.println("Kalimat Testing: [" + getSHA1_32BIT("kalimattesting") + "]");
    	}
    	catch (Exception e)
    	{
    		System.out.println("[HitIndomaretPPOB] Error Exception");
    		e.printStackTrace();
    	}
    }
    
    private static void HitZonatik()
    {
    	try
    	{
    		String sDateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//    		String sTimeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss").format(new Date());
//    		String sPaymentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:sss").format(new Date());
    		
    		String sInstCode = "BIGTV_PRE";	//KSP NASARI
    		String sMsisdn = "";
    		String sAmount = "";
//Inquiry
    		String sURL = "http://127.0.0.1:10011/";	//INDOMARET PPOB
    		String sTrxID = getNewTrxID();
    		String sData = 	"type=xml&biller=" + sInstCode + "&data=<request_topup>"
	    				+ "<bank_id>0008</bank_id>"
	    				+ "<merch_type>0001</merch_type>"
	    				+ "<terminal_id>0001</terminal_id>"
	    				+ "<date_time>" + sDateTime + "</date_time>"
	    				+ "<inst_code>" + sInstCode + "</inst_code>"
	    				+ "<stan>ABC123</stan>"
	    				+ "<trx_id>" + sTrxID + "</trx_id>"
	    				+ "<url>http%3A%2F%2F10.8.3.205/echo0.php?biller=BIGTV</url>"
	    				+ "<data>"
	    				+ "<msisdn>" + sMsisdn + "</msisdn>"
	    				+ "<amount>" + sAmount + "</amount>"
	    				+ "</data>"
	    				+ "</request_topup>";
    		
//    		sendHTTPURLConnGET(sURL + sData);
    		sendHTTPURLConnPOST(sURL, sData);
    	}
    	catch (Exception e)
    	{
    		System.out.println("[HitZonatik] Error Exception");
    		e.printStackTrace();
    	}
    }
    
//============================================================================= HitPPOB_XML ====================================================================================
    private static void HitPPOB_XML()	//TODO
    {
    	try
    	{
    		String sDateTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    		String sTrxID = getNewTrxID();
    		String sXML = "";
    		
//    		String sURL = "http://202.169.43.53:52178/transaction";	//PPOB bluepay
//    		String sURL = "http://127.0.0.1:10004/transaction";	//PPOB XPRO
    		String sURL = "http://202.169.43.53:52190/transaction"; //ottopay
//    		String sURL = "http://10.8.3.205:52195/ppob_finnet.php" ;    
    		
    		
//    		String sJenisTrx = "TOPUP_PULSA";
//    		String sJenisTrx = "TOPUP_PAKET_DATA";
//     		String sJenisTrx = "TOPUP_PAKET_DATA_SOAP";
//    		String sJenisTrx = "GAME";

//    		String sJenisTrx = "BOLT_PRE";
    		String sJenisTrx = "PLN";
//    		String sJenisTrx = "PAM_AETRA";
//    		String sJenisTrx = "TELCO_POSPAID";
//    		String sJenisTrx = "MULTIFINANCE";
//    		String sJenisTrx = "PAM_SURABAYA";
//    		String sJenisTrx = "XL_POST";
//    		String sJenisTrx = "PBB";
//    		String sJenisTrx = "BPJS";
//    		String sJenisTrx = "SAMSAT";
//    		    		
    		int iType = 1;
    		
//Topup Pulsa =============================================================================================
    		if (sJenisTrx.equalsIgnoreCase("TOPUP_PULSA"))
    		{
    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
					 		"<request_topup>" +
					 		"<partner_id>DEKAPE</partner_id>" +
					 		"<terminal_type>6012</terminal_type>" +
					 		"<product_code>1004</product_code>" +
					 		"<date_time>20190404151904</date_time>" +
					 		"<date_time>" + sDateTime + "</date_time>" +
					 		"<trx_id>123456</trx_id>" +
					 		"<trx_id>" + sTrxID + "</trx_id>" +
					 		"<terminal_id>" + "0001" + "</terminal_id>" +
//					 		"<signature_id>ff4b3e29b0db3e202ba9bafc745933d0d79af575</signature_id>" +
					 		"<data>" +
					 		"<cust_id>628960002200</cust_id>" +	
					 		"<amount>5000</amount>" +
					 		"</data>" +
					 		"</request_topup>";
    		}
    		
    		else if (sJenisTrx.equalsIgnoreCase("TOPUP_PAKET_DATA_SOAP"))
    		{
//    			sXML	=	"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:req=\"http://schemas.xl.co.id/cle/namespace/Public/Common/RequestHeaderDMZ.xsd\" xmlns:v2=\"http://schemas.xl.co.id/esb/payloads/Bil/UpfrontDiscount/WebInfo/V2.0\">" +
//    					    "<soapenv:Header/>"+
//    					    "<soapenv:Body>" +
//					 		"<req:ReqID>123457</req:ReqID>" +
//					 		"<req:IMEI>12345</req:IMEI>" +
//					 		"</req:Header>" +
//					 		"<v2:InvokeModernChannelRq>" +
//					 		"<v2:Sys_Date>2017/07/26 14:43:19</v2:Sys_Date>" +
//					 		"<v2:TRX_ID>KSPIDS####2017072000007680000</v2:TRX_ID>" +
//					 		"<v2:Switching_ID>Mitracomm</v2:Switching_ID>" +
//					 		"<v2:Merchant_ID>KSPIDS</v2:Merchant_ID>" +
//					 		"<v2:Sub_Merchant_ID>000000</v2:Sub_Merchant_ID>" +	
//					 		"<v2:DompulMSISDN>6287783242327</v2:DompulMSISDN>" +
//					 		"<v2:PIN>1234</v2:PIN>" +
//					 		"<v2:Channel_ID>6012</v2:Channel_ID>" +
//    						"<v2:msisdnB>087783242329</v2:msisdnB>" +
//    						"<v2:ProductCode>MCCX39</v2:ProductCode>" +
//    						"</v2:InvokeModernChannelRq>" +
//    						"</soapenv:Body>" +
//    						"</soapenv:Envelope>" ;
    						
    		}

    		else if (sJenisTrx.equalsIgnoreCase("TOPUP_PAKET_DATA"))
    		{
    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
					 		"<request_topup>" +
					 		"<partner_id>DEKAPE</partner_id>" +
					 		"<terminal_type>6012</terminal_type>" +
					 		"<product_code>1201</product_code>" +
					 		"<date_time>20181023103626</date_time>" +
					 		"<date_time>" + sDateTime + "</date_time>" +
					 		"<trx_id>123456</trx_id>" +
					 		"<trx_id>" + sTrxID + "</trx_id>" +
					 		"<terminal_id>" + "0001" + "</terminal_id>" +
					 		"<data>" +
					 		"<cust_id>628199970004</cust_id>" +	
					 		"<amount>189000</amount>" +
					 		"<paycode>DXL007</paycode>" +
					 		"</data>" +
					 		"</request_topup>";
    		}
    		
    		
    		else if (sJenisTrx.equalsIgnoreCase("BOLT_PRE"))
    		{
//    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//					 		"<request_topup>" +
//					 		"<partner_id>ISP</partner_id>" +
//					 		"<terminal_type>6012</terminal_type>" +
//					 		"<product_code>1060</product_code>" +
//					 		"<date_time>20150120084050</date_time>" +
//					 		"<date_time>" + sDateTime + "</date_time>" +
//					 		"<trx_id>123</trx_id>" +
//					 		"<trx_id>" + sTrxID + "</trx_id>" +
//					 		"<data>" +
//					 		"<cust_id>8410000373</cust_id>" +	
//					 		"<amount>25000</amount>" +
//					 		"</data>" +
//					 		"</request_topup>";
    		}
    		
    		
    		else if (sJenisTrx.equalsIgnoreCase("GAME"))
    		{
    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
					 		"<request_payment>" +
					 		"<partner_id>DEKAPE</partner_id>" +
					 		"<terminal_type>6012</terminal_type>" +
					 		"<product_code>7001</product_code>" +
					 		"<date_time>20150120084050</date_time>" +
					 		"<date_time>" + sDateTime + "</date_time>" +
					 		"<trx_id>123</trx_id>" +
					 		"<trx_id>" + sTrxID + "</trx_id>" +
					 		"<data>" +
					 		"<cust_id>086767676578</cust_id>" +	
					 		"<game_id>001</game_id>" +	
					 		"<amount>10000</amount>" +
					 		"</data>" +
					 		"</request_payment>";
    		}
    		
    		else if (sJenisTrx.equalsIgnoreCase("PAM_SURABAYA"))
    		{
    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
					 		"<request_inquiry>" +
					 		"<partner_id>JKP</partner_id>" +
					 		"<terminal_type>6012</terminal_type>" +
					 		"<product_code>2033</product_code>" +
					 		"<date_time>20190128174505</date_time>" +
					 		"<date_time>" + sDateTime + "</date_time>" +
					 		"<trx_id>12345678</trx_id>" +
					 		"<trx_id>" + sTrxID + "</trx_id>" +
					 		"<data>" +
					 		"<cust_id>1000029</cust_id>" +	
					 		"</data>" +
					 		"</request_inquiry>";
    		}
//////    			
   			
    			
//    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//				 		"<request_payment>" +
//				 		"<partner_id>DEKAPE</partner_id>" +
//				 		"<terminal_type>6012</terminal_type>" +
//				 		"<product_code>2034</product_code>" +
////				 		"<date_time>20150120084050</date_time>" +
//				 		"<date_time>" + "20181115112342" + "</date_time>" +
////				 		"<trx_id>123</trx_id>" +
//				 		"<trx_id>" + "18BFB2342001" + "</trx_id>" +
//				 		"<data>" +
//				 		"<cust_id>9990312305</cust_id>"+
//				 		"<cust_name>KOSASIH H</cust_name>" + 
//				 		"<repeat>1</repeat>" + 
//				 		"<detail>" +
//				 		"<period>201808#201805</period>" +
//				 		"<amount>45000</amount>" +
//				 		"<penalty>5000</penalty>"+
//				 		"<cubic>00001000-00002500</cubic>" +
//				 		"</detail>"+
//				 		"</data>" +
//				 		"</request_payment>";
    			
//    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//				 		"<request_reversal>" +
//				 		"<partner_id>ISP</partner_id>" +
//				 		"<terminal_type>6012</terminal_type>" +
//				 		"<product_code>3050</product_code>" +
////				 		"<date_time>20150120084050</date_time>" +
//				 		"<date_time>" + "20171218152128" + "</date_time>" +
////				 		"<trx_id>123</trx_id>" +
//				 		"<trx_id>" + "17CIF2100001" + "</trx_id>" +
//				 		"<data>" +
//				 		"<cust_id>11007336</cust_id>"+
//				 		"<cust_name>JONO</cust_name>" + 
//				 		"<repeat>1</repeat>" + 
//				 		"<detail>" +
//				 		"<period>201801,201712</period>" +
//				 		"<amount>60000</amount>" +
//				 		"<penalty>10000</penalty>"+
//				 		"<cubic>00001000-00001500</cubic>" +
//				 		"</detail>"+
//				 		"</data>" +
//				 		"</request_reversal>";
////    			
//////    			
////////    			
//    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//				 		"<request_payment_advice>" +
//				 		"<partner_id>ISP</partner_id>" +
//				 		"<terminal_type>6012</terminal_type>" +
//				 		"<product_code>1000</product_code>" +
////				 		"<date_time>20150120084050</date_time>" +
//				 		"<date_time>" + "20171218152128" + "</date_time>" +
////				 		"<trx_id>123</trx_id>" +
//				 		"<trx_id>" + "17CIF2100001" + "</trx_id>" +
//				 		"<data>" +
//				 		"<cust_id>3076594</cust_id>"+
//				 		"<cust_name>LAMA</cust_name>" + 
//				 		"<retribution>0</retribution>"+
//				 		"<tariff>3A</tariff>" + 
//				 		"<repeat>1</repeat>" + 
//				 		"<detail>" +
//				 		"<period>1/201605</period>" +
//				 		"<amount>7640</amount>" +
//				 		"<penalty>0</penalty>"+
//				 		"<cubic>00000091-00000094</cubic>" +
//				 		"</detail>"+
//				 		"</data>" +
//				 		"</request_payment_advice>";
//    		}
    		
    		else if (sJenisTrx.equalsIgnoreCase("BPJS"))
    		{
//    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//					 		"<request_inquiry>" +
//					 		"<partner_id>TESTBPJS</partner_id>" +
//					 		"<terminal_type>6012</terminal_type>" +
//					 		"<product_code>8001</product_code>" +
//					 		"<date_time>20190128174505</date_time>" +
//					 		"<date_time>" + sDateTime + "</date_time>" +
//					 		"<trx_id>12345678</trx_id>" +
//					 		"<trx_id>" + sTrxID + "</trx_id>" +
//					 		"<data>" +
//					 		"<cust_id>8888801261111127</cust_id>" + "<period>5</period>" +	
//					 		"</data>" +
//					 		"</request_inquiry>";
////    		
//////    			
   			
    			
    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
				 		"<request_payment>" +
				 		"<partner_id>TESTBPJS</partner_id>" +
				 		"<terminal_type>6012</terminal_type>" +
				 		"<product_code>8001</product_code>" +
//				 		"<date_time>20150120084050</date_time>" +
				 		"<date_time>" + "20190228090116" + "</date_time>" +
//				 		"<trx_id>123</trx_id>" +
				 		"<trx_id>" + "112233445" + "</trx_id>" +
				 		"<data>" +
				 		"<cust_id>8888801261111127</cust_id>"+
				 		"<cust_name>KOP. PEGAWAI PT. GAS NEGARA KT</cust_name>" + 
				 		"<total_amount>162500</total_amount>" + 
				 		"<amount>160000</amount>" +
    					"<admin_fee>2500</admin_fee>" +
				 		"<period>05</period>" +
				 		"<total_person>2</total_person>"+
				 		"<no_reff>11F435C1C0E1TEST</no_reff>" +
				 		"</data>" +
				 		"</request_payment>";	
    		}
    		
    		else if (sJenisTrx.equalsIgnoreCase("SAMSAT"))
    		{
    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
					 		"<request_inquiry>" +
					 		"<partner_id>TESTBPJS</partner_id>" +
    						"<bank_id>200</bank_id>" +
					 		"<terminal_type>6012</terminal_type>" +
					 		"<product_code>2011</product_code>" +
					 		"<date_time>20190425104505</date_time>" +
					 		"<date_time>" + sDateTime + "</date_time>" +
					 		"<trx_id>12345678</trx_id>" +
//					 		"<trx_id>" + sTrxID + "</trx_id>" +
    						"<terminal_id>12345678</terminal_id>" +
    						"<signature_id>7e65b9231cfe3468fad5ec716c60734b5f93f2c0</signature_id>" +
					 		"<data>" +
					 		"<cust_id>0634010189</cust_id>" + "<cust_no>1234567890123456</cust_no>" +	
					 		"</data>" +
					 		"</request_inquiry>";
    		}
////    		
//////    			
   			
    			
////    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//				 		"<request_payment>" +
//				 		"<partner_id>TESTBPJS</partner_id>" +
//				 		"<terminal_type>6012</terminal_type>" +
//				 		"<product_code>8001</product_code>" +
////				 		"<date_time>20150120084050</date_time>" +
//				 		"<date_time>" + "20190228090116" + "</date_time>" +
////				 		"<trx_id>123</trx_id>" +
//				 		"<trx_id>" + "112233445" + "</trx_id>" +
//				 		"<data>" +
//				 		"<cust_id>8888801261111127</cust_id>"+
//				 		"<cust_name>KOP. PEGAWAI PT. GAS NEGARA KT</cust_name>" + 
//				 		"<total_amount>162500</total_amount>" + 
//				 		"<amount>160000</amount>" +
//    					"<admin_fee>2500</admin_fee>" +
//				 		"<period>05</period>" +
//				 		"<total_person>2</total_person>"+
//				 		"<no_reff>11F435C1C0E1TEST</no_reff>" +
//				 		"</data>" +
//				 		"</request_payment>";	
//    		}
//    		else if (sJenisTrx.equalsIgnoreCase("PBB"))
//    		{
//    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//					 		"<request_inquiry>" +
//					 		"<partner_id>ISP</partner_id>" +
//					 		"<terminal_type>6012</terminal_type>" +
//					 		"<product_code>1100</product_code>" +
//	//				 		"<date_time>20171218142128</date_time>" +
//					 		"<date_time>" + sDateTime + "</date_time>" +
//		//			 		"<trx_id>17CIF3104001</trx_id>" +
//				 		    "<trx_id>" + sTrxID + "</trx_id>" +
//					 		"<data>" +
//					 		"<cust_id>317800800300700779</cust_id>" +	
//					 		"<period>2016</period>" +	
//					 		"</data>" +
//					 		"</request_inquiry>";
////			
   			
    			
//    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//				 		"<request_payment>" +
//				 		"<partner_id>ISP</partner_id>" +
//				 		"<terminal_type>6012</terminal_type>" +
//				 		"<product_code>1100</product_code>" +
//				 		"<date_time>20171218152128</date_time>" +
				// 		"<date_time>" + "20171218152128" + "</date_time>" +
//				 		"<trx_id>123</trx_id>" +
//				 		"<trx_id>" + "17CIF2100001" + "</trx_id>" +
//				 		"<data>" +
//				 		"<cust_id>317800800300700779</cust_id>" +	
//				 		"<period>2016</period>" +
//				 		"<cust_name>WAJIB PAJAK 8</cust_name>" + 
//				 		"<address>JL SUDIRMAN 8</address>" + 
//				 		"<district>JAKARTA</district>" + 
//				 		"<amount>59000</amount>" +
//				 		"<penalty>0</penalty>" +
//				 		"<info></info>" +              
//				 		"</data>" +
//				 		"</request_payment>";
////    			
    			
//    			sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//				 		"<request_reversal>" +
//				 		"<partner_id>ISP</partner_id>" +
//				 		"<terminal_type>6012</terminal_type>" +
//				 		"<product_code>1100</product_code>" +
//				 		"<date_time>20171218152128</date_time>" +
//				// 		"<date_time>" + "20171218152128" + "</date_time>" +
////				 		"<trx_id>123</trx_id>" +
//				 		"<trx_id>" + "17CIF2100001" + "</trx_id>" +
//				 		"<data>" +
//				 		"<cust_id>317800800300700767</cust_id>" +	
//				 		"<period>2016</period>" +
//				 		"<cust_name>WAJIB PAJAK 8</cust_name>" + 
//				 		"<address>JL SUDIRMAN 8</address>" + 
//				 		"<district>JAKARTA</district>" + 
//				 		"<amount>58000</amount>" +
//				 		"<penalty>0</penalty>" +
//				 		"<info>KOTA BEKASI|JAWA BARAT|72|90|20180910|BEKASI SELATAN|200420|0</info>" +              
//				 		"</data>" +
//				 		"</request_reversal>";
    			
//    			
//////    			

//    		}
    		
    		else if (sJenisTrx.equalsIgnoreCase("XL_POST"))
//    		{
//        		String sCustID = "081123456701";
//        		String sCustID = "62819000027";
//        		String sResultSignature = convertSHA1("DEKAPE"+"1303"+sTrxID+sDateTime+sCustID+"D3k27E776b");
        		
        		
    			if (iType == 1)	//Inquiry
    			{
    				sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
					 		"<request_inquiry>" +
					 		"<partner_id>D</partner_id>" +
					 		"<terminal_type>6012</terminal_type>"+
					 		"<product_code>1303</product_code>"+
					 		"<date_time>"+ sDateTime+ "</date_time>"+
					 		"<trx_id>" + sTrxID + "</trx_id>"+
					 		"<data>"+
//					 		"<cust_id>" + sCustID + "</cust_id>" +
					 		"<amount>" + "20000" + "</amount>" +
					 		"<flag>1</flag>" +
					 		"</data>"+
					 		"</request_inquiry>";
    			}
    			else if (iType == 2)	//Payment
    			{
//    				sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//					 		"<request_payment>" +
//					 		"<partner_id>ISP</partner_id>" +
//					 		"<terminal_type>6012</terminal_type>" +
//					 		"<product_code>5010</product_code>" +
//					 		"<date_time>20150120084050</date_time>" +
//					 		"<date_time>" + sDateTime + "</date_time>" +
//					 		"<trx_id>123</trx_id>" +
//					 		"<trx_id>" + sTrxID + "</trx_id>" +
//					 		"<data>" +
//					 		"<cust_id>" + sCustID + "</cust_id>" +	
//					 		"<meter_id>65432119870</meter_id>" +
//					 		"<flag>1</flag>" +
//					 		"<reffno_pln>PLN000003</reffno_pln>" +
//					 		"<reffno_me>ME100003</reffno_me>" +
//					 		"<cust_name>PT MAHAMERU GAJAYANA</cust_name>"+
//					 		"<segment>SEGMENT3</segment>"+
//					 		"<category>CAT03</category>"+
//					 		"<distribute_code>001</distribute_code>"+
//					 		"<upj>UPJ1</upj>"+
//					 		"<upj_phone>08197973897</upj_phone>"+
//					 		"<kwh_limit>100</kwh_limit>"+
//					 		"<amount>20000</amount>"+
//					 		"<token_unsold></token_unsold>" +
//					 		"</data>" +
//		   					"</request_payment>";
    			}
    			else if (iType == 3)	//Reversal
    			{
//    				sXML	=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//					 		"<request_payment_advice>" +
//					 		"<partner_id>ISP</partner_id>" +
//					 		"<terminal_type>6012</terminal_type>" +
//					 		"<product_code>5010</product_code>" +
//					 		"<date_time>20150120084050</date_time>" +
//"<date_time>" + "20171214115801" + "</date_time>" +
//	"<trx_id>123</trx_id>" +
//	"<trx_id>" + "17CEB5801001" + "</trx_id>" +
//					 		"<data>" +
//					 		"<cust_id>" + sCustID + "</cust_id>" +	
//					 		"<meter_id>65432119870</meter_id>" +
//					 		"<flag>1</flag>" +
//					 		"<reffno_pln>PLN000003</reffno_pln>" +
//					 		"<reffno_me>ME100003</reffno_me>" +
//					 		"<cust_name>PT MAHAMERU GAJAYANA</cust_name>"+
//					 		"<segment>SEGMENT3</segment>"+
//					 		"<category>CAT03</category>"+
//					 		"<distribute_code>001</distribute_code>"+
//					 		"<upj>UPJ1</upj>"+
//					 		"<upj_phone>08197973897</upj_phone>"+
//					 		"<kwh_limit>100</kwh_limit>"+
//					 		"<amount>20000</amount>"+
//					 		"<token_unsold></token_unsold>" +
//					 		"</data>" +
//		   					"</request_payment_advice>";
    			}
//    		}
    		
    		else if (sJenisTrx.equalsIgnoreCase("PAM_BANDUNG"))
//    		{
//        		String sCustID = "081123456701";
//        		String sCustID = "654321198700";
//    			if (iType == 1)	//Inquiry
//    			{
//    				sXML	=	"<request_inquiry> <partner_id>ISP</partner_id><terminal_type>6012</terminal_type><product_code>3070</product_code><date_time>20170726154124</date_time><trx_id>170726000037</trx_id><terminal_id>0118</terminal_id><data><cust_id>00411850003</cust_id></data></request_inquiry>";
//    			}
//    			else if (iType == 2)	//Payment
//    			{
//    				sXML	=	"<request_payment><partner_id>ISP</partner_id><terminal_type>6012</terminal_type><product_code>3070</product_code><date_time>20170726154124</date_time><trx_id>170726000037</trx_id><terminal_id>0118</terminal_id><data><cust_id>00411850003</cust_id><cust_name>JOY FREN</cust_name><repeat>1</repeat><detail><period>201303</period><amount>70000</amount><penalty>5000</penalty><cubic>00001000-00001500</cubic></detail></data></request_payment>";
//    			}
//    			else if (iType == 3)	//Reversal
//    			{
//    				sXML	=	"<request_reversal><partner_id>ISP</partner_id><terminal_type>6012</terminal_type><product_code>3070</product_code><date_time>20170726154124</date_time><trx_id>170726000037</trx_id><terminal_id>0118</terminal_id><data><cust_id>00411850003</cust_id><cust_name>JOY FREN</cust_name><repeat>1</repeat><detail><period>201303</period><amount>70000</amount><penalty>5000</penalty><cubic>00001000-00001500</cubic></detail></data></request_reversal>";
//    			}
//    		}

//TELCO POSTPAID ==========================================================================================
//    		else if (sJenisTrx.equalsIgnoreCase("TELCO_POSPAID"))
//    		{
//        		String sCustID = "081123456701";
//        		String sCustID = "0218445600";
//    			if (iType == 1)	//Inquiry
    			{
//    				sXML =	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//    						"<request_inquiry>" +
//    						"<partner_id>OVO_test</partner_id>" +
//    						"<terminal_type>6012</terminal_type>" +
//	       			 		"<product_code>1301</product_code>" +
//	       			 		"<date_time>" + sDateTime + "</date_time>" +
//	       			 		"<trx_id>" + sTrxID + "</trx_id>" +
//	       			 		"<data>" +
//	       			 		"<cust_id>" + sCustID + "</cust_id>" +
//	       			 		"</data>" +
//	       			 		"</request_inquiry>";
    			}
    			else if (iType == 2)	//Payment
    			{
    		    	String sCustName = "FINA TELIKA";
    		    	String sAmount = "217399";
    		    	String sDueDate = "FEB17";
    		   		sXML =	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		   					"<request_payment>" +
		   					"<partner_id>OVO_test</partner_id>" +
		   					"<terminal_type>6012</terminal_type>" +
		   					"<product_code>1301</product_code>" +
		   					"<date_time>" + sDateTime + "</date_time>" +
		   					"<trx_id>" + sTrxID + "</trx_id>" +
		   					"<data>" +
//		   						"<cust_id>" + sCustID + "</cust_id>" +
		   						"<cust_name>" + sCustName + "</cust_name>" +
		   						"<amount>" + sAmount + "</amount>" +
		   						"<due_date>" + sDueDate + "</due_date>" +
		   					"</data>" +
		   					"</request_payment>";
    			}
    			else if (iType == 3)	//Reversal
    			{
    		    	String sCustName = "FINA TELIKA";
    		    	String sAmount = "217399";
    		    	String sDueDate = "FEB17";
    		    	sTrxID = "172AH5151001";
    		    	sDateTime = "20170210175151";
    		   		sXML = 	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		   					"<request_reversal>" +
		   					"<partner_id>OVO_test</partner_id>" +
		   					"<terminal_type>6012</terminal_type>" +
		   					"<product_code>1301</product_code>" +
		   					"<date_time>" + sDateTime + "</date_time>" +
		   					"<trx_id>" + sTrxID + "</trx_id>" +
		   					"<data>" +
//		   						"<cust_id>" + sCustID + "</cust_id>" +
		   						"<cust_name>" + sCustName + "</cust_name>" +
		   						"<amount>" + sAmount + "</amount>" +
		   						"<due_date>" + sDueDate + "</due_date>" +
		   					"</data>" +
		   					"</request_reversal>";
    			}
//    		}

//MULTIFINANCE (LOAN) ===========================================================================================
    		else if (sJenisTrx.equalsIgnoreCase("MULTIFINANCE"))
    		{
//        		String sCustID = "00601106";
    			if (iType == 1)	//Inquiry
    			{
//    				sXML =	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//    						"<request_inquiry>" +
//    						"<bank_id>0008</bank_id>" +
//    						"<merch_type>6011</merch_type>" +
//    						"<date_time>" + sDateTime + "</date_time>" +
//	       			 		"<inst_code>70009</inst_code>" +
//	       			 		"<stan>705784</stan>" +
//	       			 		"<trx_id>" + sTrxID + "</trx_id>" +
//	       			 		"<terminal_id>BEIIIDJA</terminal_id>" +
//	       			 		"<url>http%3A%2F%2F10.8.3.207%2Fapache_https%2F_send_here%2Findex.php</url>" +
//	       			 		"<data>" +
//	       			 			"<cust_id>" + sCustID + "</cust_id>" +
//	       			 			"<amount></amount>" +
//	       			 		"</data>" +
//	       			 		"</request_inquiry>";
    			}
    			else if (iType == 2)	//Payment
    			{
    		    	String sCustName	= "EUIS ROKAYAH";
    		    	String sAmount 		= "1125110";
    		    	String sInstallment	= "13";
    		    	String sDueDate 	= "2017-02-08";
    		    	String sAdminFee 	= "5500";
    		   		sXML =	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		   					"<request_payment>" +
    						"<bank_id>0008</bank_id>" +
    						"<merch_type>6011</merch_type>" +
    						"<date_time>" + sDateTime + "</date_time>" +
	       			 		"<inst_code>70009</inst_code>" +
	       			 		"<stan>705784</stan>" +
	       			 		"<trx_id>" + sTrxID + "</trx_id>" +
	       			 		"<terminal_id>BEIIIDJA</terminal_id>" +
	       			 		"<url>http%3A%2F%2F10.8.3.207%2Fapache_https%2F_send_here%2Findex.php</url>" +
	       			 		"<data>" +
//	       			 			"<cust_id>" + sCustID + "</cust_id>" +
	       			 			"<cust_name>" + sCustName + "</cust_name>" +
	       			 			"<amount>" + sAmount + "</amount>" +
	       			 			"<no_installment>" + sInstallment + "</no_installment>" +
	       			 			"<due_date>" + sDueDate + "</due_date>" +
	       			 			"<admin_fee>" + sAdminFee + "</admin_fee>" +
	       			 		"</data>" +
		   					"</request_payment>";
    			}
    			else if (iType == 3)	//Reversal
    			{
    		    	String sCustName	= "EUIS ROKAYAH";
    		    	String sAmount 		= "1125110";
    		    	String sInstallment	= "13";
    		    	String sDueDate 	= "2017-02-08";
    		    	String sAdminFee 	= "5500";
    		    	sTrxID = "1746B2755001";
    		    	sDateTime = "20170406112755";
    		   		sXML =	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
		   					"<request_reversal>" +
    						"<bank_id>0008</bank_id>" +
    						"<merch_type>6011</merch_type>" +
    						"<date_time>" + sDateTime + "</date_time>" +
	       			 		"<inst_code>70009</inst_code>" +
	       			 		"<stan>705784</stan>" +
	       			 		"<trx_id>" + sTrxID + "</trx_id>" +
	       			 		"<terminal_id>BEIIIDJA</terminal_id>" +
	       			 		"<url>http%3A%2F%2F10.8.3.207%2Fapache_https%2F_send_here%2Findex.php</url>" +
	       			 		"<data>" +
//	       			 			"<cust_id>" + sCustID + "</cust_id>" +
	       			 			"<cust_name>" + sCustName + "</cust_name>" +
	       			 			"<amount>" + sAmount + "</amount>" +
	       			 			"<no_installment>" + sInstallment + "</no_installment>" +
	       			 			"<due_date>" + sDueDate + "</due_date>" +
	       			 			"<admin_fee>" + sAdminFee + "</admin_fee>" +
	       			 		"</data>" +
		   					"</request_reversal>";
    			}
    		}
    		
    		
//PLN POSTPAID ================================================================================================================================================
//    			sXML =	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//        			 		"<request_inquiry>" +
//        			 		"<partner_id>TEST</partner_id>" +
//        			 		"<terminal_type>6012</terminal_type>" +
//        			 		"<product_code>5002</product_code>" +
//        			 		"<date_time>20190327143014</date_time>" +
//        			 		"<trx_id>12345</trx_id>" +
//        			 		"<data>" +
//        			 		"<cust_id>246558383892</cust_id>" +
//        			 		"</data>" +
////        			 		"</request_inquiry>";
//    		String sXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//    					"<request_payment>" +
//    					"<partner_id>OVO_test</partner_id>" +
//    					"<terminal_type>6012</terminal_type>" +
//    					"<product_code>5002</product_code>" +
//    					"<date_time>20150120084050</date_time>" +
//    					"<trx_id>123</trx_id>" +
//    					"<data>" +
//    						"<cust_id>0000000006</cust_id>" +
//    						"<cust_name>PELANGGAN PLN POSTPAID</cust_name>" +
//    						"<rec_payable>4</rec_payable>" +
//    						"<rec_rest>0</rec_rest>" +
//    						"<reffno_pln>PLN000003</reffno_pln>" +
//    						"<unit_svc>UNIT A</unit_svc>" +
//    						"<svc_contact>0217900001</svc_contact>" +
//    						"<tariff>TARIF C</tariff>" +
//    						"<daya>500</daya>" +
//    						"<admin_fee>7000</admin_fee>" +
//    						"<periode>201204</periode>" +
//    						"<duedate>20120501</duedate>" +
//    						"<mtr_date>20120426</mtr_date>" +
//    						"<bill_amount>25000</bill_amount>" +
//    						"<insentif>1000</insentif>" +
//    						"<ppn>5000</ppn>" +
//    						"<penalty>3000</penalty>" +
//    						"<lwbp_last>500</lwbp_last>" +
//    						"<lwbp_crr>100</lwbp_crr>" +
//    						"<wbp_last>600</wbp_last>" +
//    						"<wbp_crr>100</wbp_crr>" +
//    						"<kvarh_last>1100</kvarh_last>" +
//    						"<kvarh_crr>100</kvarh_crr>" +
//    					"</data>" +
//    					"</request_payment>";
    		
//PLN PREPAID ================================================================================================================================================
       	  sXML=	"<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
			 		"<request_inquiry>" +
			 		"<partner_id>XPRO_TEST</partner_id>" +
			 		"<terminal_type>6012</terminal_type>" +
			 		"<product_code>4001</product_code>" +
			 		"<date_time>20150120084050</date_time>" +
			 		"<trx_id>123</trx_id>" +
			 		"<data>" +
			 		"<cust_id>411000031421</cust_id>" +
			 		"<flag>1</flag>" +					//Diset 0 jika acuannya adalah ID meteran, jika 1 acuannya adalah ID Pelanggan
			 		"</data>" +
			 		"</request_inquiry>";
//       	  sXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
//       			 	"<request_payment>" +
//					"<partner_id>XPRO_TEST</partner_id>" +
//					"<terminal_type>6010</terminal_type>" +
//					"<product_code>5001</product_code>" +
//					"<date_time>20161219144542</date_time>" +
//					"<trx_id>456</trx_id>" +
//					"<data>" +
//						"<cust_id>65432119872</cust_id>" +
//						"<meter_id>65432119872</meter_id>" +
//						"<flag>0</flag>" +
//						"<reffno_pln>PLN000002</reffno_pln>" +
//						"<reffno_me>ME100002</reffno_me>" +
//						"<cust_name>PELANGGAN PLN PREPAID</cust_name>" +
//						"<segment>SEGMENT2</segment>" +
//						"<category>CAT02</category>" +
//						"<distribute_code>001</distribute_code>" +
//						"<upj>UPJ1</upj>" +
//						"<upj_phone>08197973897</upj_phone>" +
//						"<kwh_limit>100</kwh_limit>" +
//						"<amount>50000</amount>" +
//						"<token_unsold>1000</token_unsold>" +
//					"</data>" +
//					"</request_payment>";
    		
//    		sendHTTPSURLConnPOST_XML(sURL, sXML, "");
    		sendHTTPURLConnPOST(sURL, sXML);
    	}
    	catch (Exception e)
    	{
    		System.out.println("[HitPPOB_XML] Error Exception");
    		e.printStackTrace();
    	}
    }
    
    public static String sendHTTPURLConnGET(String sURL)
    {
	    HttpURLConnection connection;
        URL url = null;
        String sAck = "";
        try
        {
        	System.out.println("sendHTTPSURLConnGET");
            url = new URL(sURL);
            
            System.out.println("sURL = " + sURL);
            
            connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(3 * 1000);
            connection.setReadTimeout(30 * 1000);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();
            sAck = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

            if (connection != null)
            {
                connection.disconnect();
                connection = null;
            }
            System.out.println("Response = " + sAck);
        }
        catch (Exception e)
        {
            System.out.println("Error send to " + sURL);
            e.printStackTrace();
            return null;
        }
        return sAck;
    }
    
 	private static String sendHTTPURLConnPOST(String sURL, String sPostData)
    {
	    HttpURLConnection connection;
        URL url = null;
        String sAck = "";
//        sPostData = "type=xml&data=" + sPostData;
        
        System.out.println("sURL = " + sURL);
        System.out.println("sPostData = " + sPostData);
//        System.out.println("sResponse = " + sResponse);
        try
        {
            url = new URL(sURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(3 * 1000);
            connection.setReadTimeout(30 * 1000);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestProperty(HttpHeaders.CONTENT_TYPE,MimeTypes.FORM_ENCODED);
//            connection.setRequestProperty("Content-Type", "text/xml");

            // Send Post Data
            connection.getOutputStream().write(sPostData.getBytes());
            connection.getOutputStream().flush();

            connection.connect();
            sAck = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

            if (connection != null)
            {
                connection.disconnect();
                connection = null;
            }

            System.out.println("Response = " + sAck);
            return sAck;
        }
        catch (Exception e)
        {
        	System.out.println("Cannot Send Request To ...... [" + sPostData + "]\n[" + e + "]");
            return "";
        }
    }
 	
 	private static String sendHTTPSURLConnPOST_XML(String sURL, String sPostData, String sKeyMessage) throws Exception
    {
// 		System.setProperty("jdk.certpath.disabledAlgorithms", "");
// 		Security.setProperty("jdk.tls.disabledAlgorithms", "");
 		
// 		Security.setProperty("jdk.certpath.disabledAlgorithms", "MD2, RSA keySize < 1024");
// 		Security.setProperty("jdk.tls.disabledAlgorithms", "SSLv3, RC4, DH keySize < 768");
 		
 		System.out.println("sURL = " + sURL);
 		System.out.println("sPostData = " + sPostData);
    	SSLContext sc;
        CustomTrustManager customTrustManager = new CustomTrustManager();
        TrustManager[] tma = { customTrustManager };
        SSLSocketFactory ssf;
        
        sc = SSLContext.getInstance("SSLv3");
        sc.init(null, tma, null);
        ssf = sc.getSocketFactory();
        HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
        
 		System.out.println("sendHTTPSURLConnPOST");
        URL url = null;
        String sAck = "";
int responseCode= 0;
        HttpsURLConnection connection = null;
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        try
        {
            url = new URL(sURL);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(3*1000);
            connection.setReadTimeout(30*1000);
//            connection.setRequestProperty(HttpHeaders.CONTENT_TYPE,MimeTypes.FORM_ENCODED);
            connection.setRequestProperty("Content-Type", "text/xml");

            OutputStream out = connection.getOutputStream();
            out.write(sPostData.getBytes());
            out.flush();

            responseCode = connection.getResponseCode();
            System.out.println("Connection Response Code  : " + responseCode);

            InputStream in = connection.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
            String inputLine;
            String strResponse = "";
//            String strResponse = buffer.readLine();
            
    	    while ((inputLine = buffer.readLine()) != null)
    	    {
//    	    	fwtf_Result.setText(fwtf_Result.getText() + "\n" + inputLine);
    	    	strResponse = strResponse + "\n" + inputLine;
    	    }
            
            System.out.println("Response  : " + strResponse);
        	
//            System.out.println("sAck = " + sAck);
            return strResponse;
        }
        catch (Exception e)
        {
        	System.out.println("Cannot Send Request To API [" + sPostData + "] [" + e + "]");
            return "";
        }
    }

 	private static String sendHTTPSURLConnPOST(String sURL, String sPostData, String sKeyMessage) throws Exception
    {
 		System.out.println("URL = " + sURL);
 		System.out.println("PostData = " + sPostData);
    	SSLContext sc;
        CustomTrustManager customTrustManager = new CustomTrustManager();
        TrustManager[] tma = { customTrustManager };
        SSLSocketFactory ssf;
        
        sc = SSLContext.getInstance("SSLv3");
        sc.init(null, tma, null);
        ssf = sc.getSocketFactory();
        HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
        
 		System.out.println("sendHTTPSURLConnPOST");
        URL url = null;
        String sAck = "";
int responseCode= 0;
        HttpsURLConnection connection = null;
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        try
        {
        	System.out.println("AA");
            url = new URL(sURL);
            connection = (HttpsURLConnection) url.openConnection();
            System.out.println("BB");
            connection.setRequestMethod("POST");
            System.out.println("CC");
            connection.setDoInput(true);
            System.out.println("DD");
            connection.setDoOutput(true);
            System.out.println("EE");
            connection.setConnectTimeout(3*1000);
            System.out.println("FF");
            connection.setReadTimeout(30*1000);
            System.out.println("GG");
            connection.setRequestProperty("Authorization", "Basic ZG9rdS10ZXN0czpza3UxOTg0aWQ=");
//            connection.setRequestProperty(HttpHeaders.CONTENT_TYPE,MimeTypes.FORM_ENCODED);
//            connection.setRequestProperty("Content-Type", "text/xml");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.setRequestProperty("Content-Type", "text/html; charset=utf-8");
            
            System.out.println("HH");

            OutputStream out = connection.getOutputStream();
            out.write(sPostData.getBytes());
            out.flush();

            responseCode = connection.getResponseCode();
            System.out.println("Connection Response Code  : " + responseCode);

            InputStream in = connection.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
            String inputLine;
            String strResponse = "";
            
    	    while ((inputLine = buffer.readLine()) != null)
    	    {
    	    	strResponse = strResponse + "\n" + inputLine;
    	    }

            System.out.println("ALL Response  : " + strResponse);
        	
//            System.out.println("sAck = " + sAck);
            return strResponse;
        }
        catch (Exception e)
        {
        	System.out.println("Cannot Send Request To API [" + sPostData + "]\n[" + e + "]");
            return "";
        }
    }

 	private static String sendHTTPSURLConnPOST_SOAP(String sURL, String sPostData, String sKeyMessage) throws Exception
    {
 		System.out.println("URL = " + sURL);
 		System.out.println("PostData = " + sPostData);
    	SSLContext sc;
        CustomTrustManager customTrustManager = new CustomTrustManager();
        TrustManager[] tma = { customTrustManager };
        SSLSocketFactory ssf;
        
        sc = SSLContext.getInstance("SSLv3");
        sc.init(null, tma, null);
        ssf = sc.getSocketFactory();
        HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
        
 		System.out.println("sendHTTPSURLConnPOST");
        URL url = null;
        String sAck = "";
int responseCode= 0;
        HttpsURLConnection connection = null;
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        try
        {
            url = new URL(sURL);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(3*1000);
            connection.setReadTimeout(30*1000);
//            connection.setRequestProperty("Authorization", "Basic ZG9rdS10ZXN0czpza3UxOTg0aWQ=");
//            connection.setRequestProperty(HttpHeaders.CONTENT_TYPE,MimeTypes.FORM_ENCODED);
            connection.setRequestProperty("Content-Type", "text/xml");
//            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            connection.setRequestProperty("Content-Type", "text/html; charset=utf-8");

            OutputStream out = connection.getOutputStream();
            out.write(sPostData.getBytes());
            out.flush();

            responseCode = connection.getResponseCode();
            System.out.println("Connection Response Code  : " + responseCode);

            InputStream in = connection.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
            String inputLine;
            String strResponse = "";
            
    	    while ((inputLine = buffer.readLine()) != null)
    	    {
    	    	strResponse = strResponse + "\n" + inputLine;
    	    }

            System.out.println("ALL Response  : " + strResponse);
        	
//            System.out.println("sAck = " + sAck);
            return strResponse;
        }
        catch (Exception e)
        {
        	System.out.println("Cannot Send Request To API [" + sPostData + "]\n[" + e + "]");
            return "";
        }
    }
 	
 	private static String sendHTTPSURLConnGET(String sURL, String sPostData, String sKeyMessage) throws Exception
    {
 		System.out.println("sURL = " + sURL);
 		System.out.println("sPostData = " + sPostData);
    	SSLContext sc;
        CustomTrustManager customTrustManager = new CustomTrustManager();
        TrustManager[] tma = { customTrustManager };
        SSLSocketFactory ssf;
        
        sc = SSLContext.getInstance("SSLv3");
        sc.init(null, tma, null);
        ssf = sc.getSocketFactory();
        HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
        
 		System.out.println("sendHTTPSURLConnGET");
        URL url = null;
        String sAck = "";
int responseCode= 0;
        HttpsURLConnection connection = null;
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        try
        {
        	System.out.println("AA");
            url = new URL(sURL);
            connection = (HttpsURLConnection) url.openConnection();
            System.out.println("BB");
            connection.setRequestMethod("GET");
            System.out.println("CC");
            connection.setDoInput(true);
            System.out.println("DD");
            connection.setDoOutput(true);
            System.out.println("EE");
            connection.setConnectTimeout(3*1000);
            System.out.println("FF");
            connection.setReadTimeout(30*1000);
            System.out.println("GG");
            connection.setRequestProperty(HttpHeaders.CONTENT_TYPE,MimeTypes.FORM_ENCODED);
//            connection.setRequestProperty("Content-Type", "text/xml");
            System.out.println("HH");
            connection.connect();
//            OutputStream out = connection.getOutputStream();
//            out.write(sPostData.getBytes());
//            out.flush();

            responseCode = connection.getResponseCode();
            System.out.println("Connection Response Code  : " + responseCode);

            InputStream in = connection.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in));
            String inputLine;
            String strResponse = "";
    	    while ((inputLine = buffer.readLine()) != null)
    	    {
//    	    	strResponse = strResponse + "\n" + inputLine;
    	    	strResponse = strResponse + inputLine;
    	    }
            
            System.out.println("ALL Response  : " + strResponse);
            return strResponse;
        }
        catch (Exception e)
        {
        	System.out.println("Cannot Send Request To API [" + sPostData + "] [" + e + "]");
            return "";
        }
    }
 	
 	private static String SendToSalesForce_HTTPS_GET_KIBREG(String sMsisdn, String sURL, String sKeyMessage, String CampaignID) throws Exception
    {
 		System.out.println("[SendToSalesForce_HTTPS_GET_KIBREG] [" + sMsisdn + "] URL=[" + sURL + "] KeyMessage=[" + sKeyMessage + "]");
 		
    	SSLContext sc;
        CustomTrustManager customTrustManager = new CustomTrustManager();
        TrustManager[] tma = { customTrustManager };
        SSLSocketFactory ssf;
        
        sc = SSLContext.getInstance("SSLv3");
//        sc = SSLContext.getInstance("SSL");
        sc.init(null, tma, null);
        ssf = sc.getSocketFactory();
        HttpsURLConnection.setDefaultSSLSocketFactory(ssf);
        
        URL url = null;
        String strResponse = "";
        
		String sStatus = "";
		String sMessage = "";
		String sErrorCode = "";
		String sChild = "";
		String sMember_id = "";
		String sIs_new_member = "";
		
int responseCode= 0;
        HttpsURLConnection connection = null;
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        try
        {
        	System.setProperty("jsse.enableSNIExtension", "false");	//Khusus JRE 7
            url = new URL(sURL);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setConnectTimeout(3*1000);
            connection.setReadTimeout(30*1000);
//            connection.setRequestProperty("Authorization", "Bearer " + sKeyMessage);
//            connection.setRequestProperty(HttpHeaders.CONTENT_TYPE,MimeTypes.FORM_ENCODED);
            connection.connect();
            
            strResponse = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
            
            responseCode = connection.getResponseCode();
            System.out.println("[SendToSalesForce_HTTPS_GET_KIBREG] [" + sMsisdn + "] Connection Response Code : " + responseCode);
            System.out.println("[SendToSalesForce_HTTPS_GET_KIBREG] [" + sMsisdn + "] Connection Response Code : " + connection.getResponseMessage());
            
            if (connection != null){
                connection.disconnect();
                connection = null;
            }

            System.out.println(" Response from Sales Force Saasten : [" + strResponse + "]");

//            System.out.println("strResponse = " + strResponse);
            
        }
        catch (Exception e)
        {
        	System.out.println("[SendToSalesForce_HTTPS_GET_KIBREG] [" + sMsisdn + "] Cannot Send Request To Sales Force Saasten [" + sURL + "]" +  e);
            return "";
        }
        return strResponse;
    }
 	
	public static String sendHTTPURLConn(String sURL)
    {
	    HttpURLConnection connection;
        URL url = null;
        String sAck = "";
        try
        {
        	System.out.println("sendHTTPSURLConnGET");
            url = new URL(sURL);
            
            System.out.println("sURL = " + sURL);
            
            connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(3 * 1000);
            connection.setReadTimeout(60 * 1000);
//            connection.setReadTimeout((int) HTTP_TIMEOUT_ORANGETV);
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.connect();
            sAck = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();

            if (connection != null)
            {
                connection.disconnect();
                connection = null;
            }
            System.out.println("Ack = " + sAck);
        }
        catch (Exception e)
        {
            System.out.println("Error send to " + sURL + " = " + e);
            return null;
        }
        return sAck;
    }
	
    public static String getMD5(String text)
    {
        String val = "";

        byte[] defaultBytes = text.getBytes();
        try
        {
             MessageDigest algorithm = MessageDigest.getInstance("MD5");
             algorithm.reset();
             algorithm.update(defaultBytes);
             byte messageDigest[] = algorithm.digest();

             StringBuffer hexString = new StringBuffer();
             for (int i=0;i<messageDigest.length;i++)
             {
                 String hex = Integer.toHexString(0xFF & messageDigest[i]);
                 if(hex.length()==1)
                    hexString.append('0');

                 hexString.append(hex);
             }

             val = hexString.toString();
        }
        catch(NoSuchAlgorithmException nsae)
        {
        	System.out.println("Error -> Get MD5 = [" + nsae + "]");
        }

        return val;
    }
    
    public static String getSHA1_32BIT(String text)
    {
    	String val = "";
        try{
//            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            MessageDigest digest = MessageDigest.getInstance("SHA1");
            byte[] hash = digest.digest(text.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            val = hexString.toString();
	    } catch(Exception ex){
	    	System.out.println("Get SHA 32bit");
	    	ex.printStackTrace();
	    }
        return val;
    }
    
//    public static String getSHA1_64BIT(String text)
//    {
//        String val = "";
//
////        byte[] defaultBytes = text.getBytes();
//        try
//        {
//             MessageDigest algorithm = MessageDigest.getInstance("SHA1");
//             val = new String(BASE64EncoderStream.encode(algorithm.digest(text.getBytes())));
//             val = val.trim();
//        }
//        catch(Exception nsae)
//        {
//        	System.out.println("Get SHA 64bit\n" + nsae);
//        }
//
//        return val;
//    }
    
    static String xmlEscapeText(String t)
    {
	   StringBuilder sb = new StringBuilder();
	   for(int i = 0; i < t.length(); i++){
	      char c = t.charAt(i);
	      switch(c){
	      case '<': sb.append("&lt;"); break;
	      case '>': sb.append("&gt;"); break;
	      case '\"': sb.append("&quot;"); break;
	      case '&': sb.append("&amp;"); break;
	      case '\'': sb.append("&apos;"); break;
	      default:
	         if(c>0x7e) {
	            sb.append("&#"+((int)c)+";");
	         }else
	            sb.append(c);
	      }
	   }
	   return sb.toString();
	}
    
    public synchronized static String getNewTrxID()
    {
        /* Unique trx id consist of : time of trx + random number 
         * (padded with 0 right justified)
         */
        String sTime  = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String sYear  = sTime.substring(2, 4);
        String sMonth = getCode(sTime.substring(4, 6));
        String sDay   = getCode(sTime.substring(6, 8));
        String sHour  = getCode(sTime.substring(8, 10));
        String sRest  = sTime.substring(10);
        
        String sTimePart = sHour + sRest;
        while (sTimePart.length() < 5)
        {
            sTimePart = "0" + sTime;
        }
        
        String sNewTime = sYear + sMonth + sDay + sTimePart;
        if(VD.lastIDDate.equals(sTime))
        {
            VD.lastID++;
        }
        else
        {
            VD.lastID = 1;
            VD.lastIDDate = sTime;
        }
        
        String sNewID = String.valueOf(VD.lastID);
        while (sNewID.length() < 3)
        {
            sNewID = "0" + sNewID;
        }
        
        return new StringBuffer(12).append(sNewTime).append(sNewID).toString();
    }
    
    private static String getCode(String sSource)
    {
        Integer iTemp = new Integer(sSource);
        int iVal = iTemp.intValue();
        if (iVal < 10)
        {
            return sSource.substring(1);
        }
        else
        {
            // String arrCode[] = new String[26];
            String arrCode[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
                                 "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
            return arrCode[iVal - 10];
        }
    }
    
    public static String convertSHA1 (String data)
    {
        MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(data.getBytes());
	        byte byteData[] = md.digest();

	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++)
	        {
	        	sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }

	        return sb.toString();
		} catch (Exception e) {
			System.out.println("ERROR BOY");
		}

		return "ERROR";
    }
}
