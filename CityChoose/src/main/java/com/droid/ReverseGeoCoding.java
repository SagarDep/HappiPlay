package com.droid;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Locale;

/**
 * Created by yons on 15/10/15.
 */
public class ReverseGeoCoding {
    private String StreetNumber, Sublocality, City, State, Country, County, PIN;
    private String curLatitude;
    private String curLongitude;

    public ReverseGeoCoding(double lon, double lat) {
        this.curLatitude = String.valueOf(lat);
        this.curLongitude = String.valueOf(lon);
    }

    public String getAddress() {

        try {
            String url = "http://maps.googleapis.com/maps/api/geocode/json?" +
                    "latlng=" + curLatitude + "," + curLongitude + "&sensor=true" +
                    "&language=" + Locale.getDefault().getLanguage();
            Log.i("test", url);
            JSONObject jsonObj = ParserJson.getJSONfromURL(url);
            Log.i("test", jsonObj.toString());
            String Status = jsonObj.getString("status");
            if (Status.equalsIgnoreCase("OK")) {
                JSONArray Results = jsonObj.getJSONArray("results");
                JSONObject zero = Results.getJSONObject(0);
                JSONArray address_components = zero.getJSONArray("address_components");

                for (int i = 0; i < address_components.length(); i++) {
                    JSONObject zero2 = address_components.getJSONObject(i);
                    String long_name = zero2.getString("long_name");
                    JSONArray mtypes = zero2.getJSONArray("types");
                    String Type = mtypes.getString(0);

                    if (!TextUtils.isEmpty(long_name) || !long_name.equals("") || long_name.length() > 0 || long_name.equals("")) {
                        if (Type.equalsIgnoreCase("street_number")) {
                            StreetNumber = long_name + " ";
                        } else if (Type.equalsIgnoreCase("route")) {
                            StreetNumber = StreetNumber + long_name;
                        } else if (Type.equalsIgnoreCase("sublocality")) {
                            Sublocality = long_name;
                        } else if (Type.equalsIgnoreCase("locality")) {
                            // Sublocality = Sublocality + long_name + ", ";
                            City = long_name;
                        } else if (Type.equalsIgnoreCase("administrative_area_level_2")) {
                            County = long_name;
                        } else if (Type.equalsIgnoreCase("administrative_area_level_1")) {
                            State = long_name;
                        } else if (Type.equalsIgnoreCase("country")) {
                            Country = long_name;
                        } else if (Type.equalsIgnoreCase("postal_code")) {
                            PIN = long_name;
                        }
                    }

                    // JSONArray mtypes = zero2.getJSONArray("types");
                    // String Type = mtypes.getString(0);
                    // Log.e(Type,long_name);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getCity();
    }

    public String getStreetNumber() {
        return StreetNumber;

    }

    public String getSublocality() {
        return Sublocality;

    }

    public String getCity() {
        return City;

    }

    public String getState() {
        return State;

    }

    public String getCountry() {
        return Country;

    }

    public String getCounty() {
        return County;

    }

    public String getPIN() {
        return PIN;

    }

}
