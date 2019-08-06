package com.example.homework01.service.csvParser;

import java.util.Map;

/**
 * Created by Chershembeev_AE
 * Date: 09.07.2019
 * Time: 17:05.
 */

public interface Parser {

    Map<String,String>getAllQuestions(String fileName);
}
