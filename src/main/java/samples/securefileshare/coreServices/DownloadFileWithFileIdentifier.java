package samples.securefileshare.coreServices;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import com.cybersource.authsdk.core.MerchantConfig;

import Api.SecureFileShareApi;
import Data.Configuration;
import Invokers.ApiClient;
import Invokers.ApiException;

public class DownloadFileWithFileIdentifier {

	private static String responseCode = null;
	private static String status = null;
	private static String responseBody=null;
	
	private static String organizationId = "testrest";
	private static Properties merchantProp;
	private  static String resourceFile = "secureFile";
    private static final String FILE_PATH = "C:\\Nov8workspace\\cybersource-rest-samples-java\\src\\test\\resources\\";
	
    private static String  fileId = "VFJSUmVwb3J0LTc4NTVkMTNmLTkzOTgtNTExMy1lMDUzLWEyNTg4ZTBhNzE5Mi5jc3YtMjAxOC0xMC0yMA==";


	public static void main(String args[]) throws Exception {
		process();
	}

	private static void process() throws Exception {

		try {
			/* Read Merchant details. */
			merchantProp = Configuration.getMerchantDetails();
			MerchantConfig merchantConfig = new MerchantConfig(merchantProp);

			SecureFileShareApi secureFileShareApi = new SecureFileShareApi();
			secureFileShareApi.getFileWithHttpInfo(fileId, organizationId, merchantConfig);
			
			responseBody=ApiClient.responseBody;
			System.out.println("responseBody:: "+responseBody);
			InputStream stream = new ByteArrayInputStream(responseBody.getBytes(StandardCharsets.UTF_8));
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
	        org.apache.commons.io.IOUtils.copy(stream, baos);
	        byte[] bytes = baos.toByteArray();
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                (new ByteArrayInputStream(bytes))));

	        String output;
	        String reportType="csv";
	        System.out.println("Output from Server .... \n");
	        while ((output = br.readLine()) != null) {
	            if(output.contains("xml")){
	                reportType = "xml";
	            }
	        }
	        //System.out.println("Wait. "+resourceFile+"."+reportType+" is getting downloaded...\n");
	        BufferedReader br_write = new BufferedReader(new InputStreamReader(
	                (new ByteArrayInputStream(bytes))));
	        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(FILE_PATH+resourceFile+"."+reportType)));
	        while ((output = br_write.readLine()) != null) {
	        	 System.out.println(output);
	  	       
	            bw.write(output+"\n");
	        }
	        System.out.println(resourceFile+"."+reportType+" is downloaded successfully");
	        bw.close();
	        

			responseCode = ApiClient.responseCode;
			status = ApiClient.status;
			System.out.println("ResponseCode :" + responseCode);
			System.out.println("ResponseMessage :" + status);

		} catch (ApiException e) {

			e.printStackTrace();
		}
	}

}
