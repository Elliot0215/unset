package com.wp.unset.action.swing;

import com.google.gson.Gson;
import com.wp.unset.action.http.track.TrackTemplateService;
import com.wp.unset.action.model.KeyResult;
import com.wp.unset.action.model.TrackResult;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class FormTestSwing {
 
    private JPanel north = new JPanel();
 
    private JPanel center = new JPanel();
 
    private JPanel south = new JPanel();

   /* private JLabel r1 = new JLabel("out:");
    private JLabel r2 = new JLabel("NULL");*/
 
    private JLabel inputVariable = new JLabel("input variable:");
    private JTextField inputVariableContent = new JTextField();
 
    private JLabel outVariable = new JLabel("out variable:");
    private JTextField outVariableContent = new JTextField();
 
    public JPanel initNorth() {
        JLabel title = new JLabel("Variable translation naming");
        title.setFont(new Font("微软雅黑", Font.PLAIN, 26));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        north.add(title);
 
        return north;
    }
 
    public JPanel initCenter() {
        center.setLayout(new GridLayout(3, 2));

        center.add(inputVariable);
        center.add(inputVariableContent);

        center.add(outVariable);
        center.add(outVariableContent);
 
        return center;
    }

    public JPanel initSouth() {
        TrackTemplateService trackTemplateService = new TrackTemplateService();
        JButton submit = new JButton("submit");
        submit.setHorizontalAlignment(SwingConstants.CENTER);
        submit.setVerticalAlignment(SwingConstants.CENTER);
        south.add(submit);

        submit.addActionListener(e -> {
            String key = inputVariableContent.getText();
            String ret = trackTemplateService.translateAccordingToVariableName(key);
            Gson gson = new Gson();
            TrackResult trackResult = gson.fromJson(ret, TrackResult.class);
            System.out.println(trackResult);
            List<List<KeyResult>> translateResult = trackResult.getTranslateResult();
            String[] retString = translateResult.get(0).get(0).getTgt().split(" " );
            StringBuilder stringBuilder = new StringBuilder();
            for(String val:retString){
                String lowerCaseVal = val.toLowerCase();
                String conversionVal = lowerCaseVal.substring(0, 1).toUpperCase() + lowerCaseVal.substring(1);
                stringBuilder.append(conversionVal);
            }
            outVariableContent.setText(stringBuilder.toString());
        });

        return south;
    }

}
