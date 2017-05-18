package com.test.servlets;

import com.google.gson.Gson;
import com.test.utils.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
	
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;

@WebServlet("/autocomplete/*")
public class AutoComplete extends HttpServlet {
    @Override
    protected void doPost(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException,
            IOException {
    	System.out.println("helo");
        final List<String> countryList = new ArrayList<String>();
        countryList.add("USA");
        countryList.add("Pakistan");
        countryList.add("Britain");
        countryList.add("India");
        countryList.add("Italy");
        countryList.add("Ireland");
        countryList.add("Bangladesh");
        countryList.add("Brazil");
        countryList.add("United Arab Emirates");
        Collections.sort(countryList);

        // Map real data into JSON

        response.setContentType("application/json");

        final String param = request.getParameter("id1");
        final List<AutoCompleteData> result = new ArrayList<AutoCompleteData>();
        for (final String country : countryList) {
            if (country.toLowerCase().startsWith(param.toLowerCase())) {
                result.add(new AutoCompleteData(country, country));
            }
        }
        response.getWriter().write(new Gson().toJson(result));
    }
}