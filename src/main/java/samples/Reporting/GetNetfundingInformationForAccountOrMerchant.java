package samples.Reporting;
import java.*;
import java.util.*;
import java.math.BigDecimal;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

import com.google.common.base.Strings;
import com.cybersource.authsdk.core.MerchantConfig;

import Api.*;
import Data.Configuration;
import Invokers.ApiClient;
import Invokers.ApiException;
import Model.*;

public class GetNetfundingInformationForAccountOrMerchant{
	private static String responseCode = null;
	private static String status = null;
	private static Properties merchantProp;

/*
	public static void main(String args[]) throws Exception 
	{
		// Accept required parameters from args[] and pass to run.
		run();
	}
*/
	public static ReportingV3NetFundingsGet200Response run(){
	
		DateTime startTime = new DateTime("2019-08-01T00:00:00.000Z").withZone(DateTimeZone.forID("GMT"));
		DateTime endTime = new DateTime("2019-09-01T23:59:59.999Z").withZone(DateTimeZone.forID("GMT"));
		String organizationId = "testrest";
		String groupName = null;

		ReportingV3NetFundingsGet200Response result = null;
		try
		{
			merchantProp = Configuration.getMerchantDetails();
			ApiClient apiClient = new ApiClient();
			MerchantConfig merchantConfig = new MerchantConfig(merchantProp);
			apiClient.merchantConfig = merchantConfig;

			NetFundingsApi apiInstance = new NetFundingsApi(apiClient);
			result = apiInstance.getNetFundingDetails(startTime, endTime, organizationId, groupName);

			responseCode = apiClient.responseCode;
			status = apiClient.status;
			System.out.println("ResponseCode :" + responseCode);
			System.out.println("ResponseMessage :" + status);
			System.out.println(result);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	return result;
	}
}
