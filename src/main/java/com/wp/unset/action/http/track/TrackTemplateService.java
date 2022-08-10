package com.wp.unset.action.http.track;


import com.wp.unset.action.utils.HttpClientUtil;

import java.net.URLEncoder;

public class TrackTemplateService implements TrackTemplate{
    @Override
    public String translateAccordingToVariableName(String key) {
        String enKey = URLEncoder.encode(key);
        return HttpClientUtil.sendGetByUrl("https://fanyi.youdao.com/translate?&doctype=json&type=AUTO&i=" + enKey, 2);
    }

}
