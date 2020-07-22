package amida.cristhian.coopsapi.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import amida.cristhian.coopsapi.ConfigurationsBase;
import amida.cristhian.coopsapi.model.request.CoopsapiScopeRequest;
import amida.cristhian.coopsapi.model.response.CoopsapiResponse;
import amida.cristhian.coopsapi.model.response.DailyValue;
import amida.cristhian.coopsapi.service.ICoopsapiService;

/**
 * @author Cristhian
 *
 */
@Service
public class CoopsapiServiceImpl implements ICoopsapiService {

	@Autowired
	ConfigurationsBase configureUrl;

	@Override
	public CoopsapiResponse findCoopsapiInformation(CoopsapiScopeRequest coopsapiRequest) throws Exception {

		String nowDate = coopsapiRequest.getDateOfService();
		String previousDate = GetPreviousMonthDate(nowDate);
		String baseUrl = configureUrl.getBaseUrl();
		String station = coopsapiRequest.getStation();
		String product = coopsapiRequest.getProduct().toString();

		return GetInformation(nowDate, previousDate, baseUrl, station, product);

	}

	private CoopsapiResponse GetInformation(String originalDate, String oldDate, String baseUrl, String station,
			String product) throws Exception {

		CoopsapiResponse coopsapiResponse = new CoopsapiResponse();

		ArrayList<DailyValue> currentData = GetDataFromApi(baseUrl, originalDate, station, product);
		ArrayList<DailyValue> previousData = GetDataFromApi(baseUrl, oldDate, station, product);

		ArrayList<DailyValue> dailyVList = new ArrayList<DailyValue>();
		Double aveDailyValue = 0.00;
		Integer count = 0;
		Integer dateCoopsapi;
		Integer check = 1;
		for (final DailyValue cuData : currentData) {

			dateCoopsapi = Integer.parseInt(cuData.getT().substring(8, 10));
			if (dateCoopsapi != check) {

				aveDailyValue = aveDailyValue / count;
				DailyValue dvvv = new DailyValue();
				dvvv.setV(String.valueOf(aveDailyValue));
				dvvv.setT(cuData.getT().substring(0, 8) + String.valueOf(check));
				dailyVList.add(dvvv);
				check = dateCoopsapi;
				aveDailyValue = 0.00;
				count = 0;
			}
			aveDailyValue += cuData.getV().isEmpty() ? 0.00 : Double.parseDouble(cuData.getV());
			count++;
		}
		coopsapiResponse.setDailyAvgMonth(dailyVList);

		coopsapiResponse
				.setMaxValueMonth(currentData.stream().max(Comparator.comparing(DailyValue::getV)).get().getV());
		String minMonthValue = currentData.stream().min(Comparator.comparing(DailyValue::getV)).get().getV();
		coopsapiResponse.setMinValueMonth(minMonthValue = minMonthValue.isEmpty() ? "0" : minMonthValue);

		Double aveMonthValue = 0.00;
		for (final DailyValue sValue : currentData)
			aveMonthValue += sValue.getV().isEmpty() ? 0.00 : Double.parseDouble(sValue.getV());
		coopsapiResponse.setAverageValueMonth(String.valueOf(aveMonthValue / currentData.size()));

		coopsapiResponse
				.setMaxValuePrevMonth(previousData.stream().max(Comparator.comparing(DailyValue::getV)).get().getV());
		String minPrevMonthValue = previousData.stream().min(Comparator.comparing(DailyValue::getV)).get().getV();
		coopsapiResponse.setMinValuePrevMonth(minPrevMonthValue = minMonthValue.isEmpty() ? "0" : minPrevMonthValue);

		Double avePrevMonthValue = 0.00;
		for (final DailyValue sValue : previousData)
			avePrevMonthValue += sValue.getV().isEmpty() ? 0.00 : Double.parseDouble(sValue.getV());
		coopsapiResponse.setAverageValuePrevMonth(String.valueOf(avePrevMonthValue / previousData.size()));

		return coopsapiResponse;
	}

	private ArrayList<DailyValue> GetDataFromApi(String urlBase, String dateCoopsapi, String station, String product) {

		RestTemplate restTemplate = new RestTemplate();
		String finalUrl = urlBase.concat("&begin_date=").concat(dateCoopsapi).concat("01&end_date=")
				.concat(dateCoopsapi).concat("30&station=").concat(station).concat("&product=").concat(product)
				.concat(";");
		String coopsapiResponse = restTemplate.getForObject(finalUrl, String.class);

		JSONObject jsonObjectResponse = new JSONObject(coopsapiResponse);
		JSONArray tsmresponse = (JSONArray) jsonObjectResponse.get("data");

		ArrayList<DailyValue> dvv = new Gson().fromJson(tsmresponse.toString(), new TypeToken<List<DailyValue>>() {
		}.getType());
		return dvv;

	}

	private String GetPreviousMonthDate(String pDate) throws Exception {

		DateFormat format = new SimpleDateFormat("yyyyMM", Locale.US);
		Date dateCoopsapi = format.parse(pDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateCoopsapi);
		cal.add(cal.MONTH, -1);

		return format.format(cal.getTime());
	}

}
