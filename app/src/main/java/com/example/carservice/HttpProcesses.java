package com.example.carservice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpProcesses {
    public String sendRequest(String strings[]){
        String type = strings[0];
        String serviceUrl="http://localhost/carservice/carservice.php";
        try {

            URL url=null;
            String insert_data="";
            if (type.equals("reg")) {

                url = new URL(serviceUrl);
                String name = strings[1];
                String phone = strings[2];
                String email = strings[3];
                String password = strings[4];
                insert_data=URLEncoder.encode("operation", "UTF-8") + "=" + URLEncoder.encode("register", "UTF-8")
                        +"&&"+URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")
                        + "&&" + URLEncoder.encode("email_address", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
                        + "&&" + URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8")
                        + "&&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            } else if (type.equals("registerCar")){
                url = new URL(serviceUrl);
                int userId= Integer.parseInt(strings[1]);
                String carModel=strings[2];
                String fuel=strings[3];
                String modelYear= strings[4];
                String engineCC=strings[5];
                String chassisNo=strings[6];
                String engineNo=strings[7];
                String numberPlate=strings[8];
                String lastInsurance=strings[9];
                int mileage= Integer.parseInt(strings[10]);
                String nextInsurance=strings[11];
                insert_data=URLEncoder.encode("operation", "UTF-8") + "=" + URLEncoder.encode("registerCar", "UTF-8")
                        +"&&"+URLEncoder.encode("userId", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(userId), "UTF-8")
                        +"&&"+URLEncoder.encode("carModel", "UTF-8") + "=" + URLEncoder.encode(carModel, "UTF-8")
                        + "&&" + URLEncoder.encode("fuel", "UTF-8") + "=" + URLEncoder.encode(fuel, "UTF-8")
                        + "&&" + URLEncoder.encode("modelYear", "UTF-8") + "=" + URLEncoder.encode(modelYear, "UTF-8")
                        + "&&" + URLEncoder.encode("engineCC", "UTF-8") + "=" + URLEncoder.encode(engineCC, "UTF-8")
                        +"&&"+URLEncoder.encode("chassisNo", "UTF-8") + "=" + URLEncoder.encode(chassisNo, "UTF-8")
                        +"&&"+URLEncoder.encode("engineNo", "UTF-8") + "=" + URLEncoder.encode(engineNo, "UTF-8")
                        +"&&"+URLEncoder.encode("numberPlate", "UTF-8") + "=" + URLEncoder.encode(numberPlate, "UTF-8")
                        +"&&"+URLEncoder.encode("lastInsurance", "UTF-8") + "=" + URLEncoder.encode(lastInsurance, "UTF-8")
                        +"&&"+URLEncoder.encode("mileage", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(mileage), "UTF-8")
                        +"&&"+URLEncoder.encode("nextInsurance", "UTF-8") + "=" + URLEncoder.encode(nextInsurance, "UTF-8");

            }else if (type.equals("login")) {
                url = new URL(serviceUrl);
                String email = strings[1];
                String password = strings[2];
                insert_data = URLEncoder.encode("operation", "UTF-8") + "=" + URLEncoder.encode("login", "UTF-8")
                        +"&&"+URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8")
                        + "&&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(insert_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "ISO-8859-1");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String result = "";
            String reg_line = "";

            StringBuilder stringBuilder = new StringBuilder();
            while ((reg_line = bufferedReader.readLine()) != null) {
                stringBuilder.append(reg_line).append("\n");
            }
            result = stringBuilder.toString();
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
