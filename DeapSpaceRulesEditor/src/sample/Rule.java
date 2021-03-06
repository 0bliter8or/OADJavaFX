package sample;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;

/**
 * Created by kroge on 7/08/2017.
 */
public class Rule {

    private static HashMap<String, TextField> inputFields;

    public void loadSelfIntoTextFields()
    {
        inputFields.get("object_type").setText(object_type);
        inputFields.get("food_P").setText(""+food_P);
        inputFields.get("water_P").setText(""+water_P);
        inputFields.get("housing_P").setText(""+housing_P);
        inputFields.get("elec_P").setText(""+elec_P);
        inputFields.get("metal_P").setText(""+metal_P);
        inputFields.get("metal_BC").setText(""+metal_BC);
        inputFields.get("minutes_BC").setText(""+minutes_BC);
        inputFields.get("elec_OC").setText(""+elec_OC);
        inputFields.get("pop_OC").setText(""+pop_OC);
        inputFields.get("health_BS").setText(""+health_BS);
        inputFields.get("speed_kmps_BS").setText(""+speed_kmps_BS);
        inputFields.get("attack_BS").setText(""+attack_BS);
        inputFields.get("armor_type_BS").setText(armor_type_BS);
        inputFields.get("weakness_BS").setText(weakness_BS);
        inputFields.get("strength_BS").setText(strength_BS);
        inputFields.get("modifier_BS").setText(""+modifier_BS);
        unsavedIndividualRuleChanges = false;
    }

    public void updateSelfFromTextFields()
    {
        unsavedRulesChanges = true;
        unsavedIndividualRuleChanges = false;
        object_type = inputFields.get("object_type").getText();
        food_P = new Double(inputFields.get("food_P").getText());
        water_P = new Double(inputFields.get("water_P").getText());
        housing_P = new Double(inputFields.get("housing_P").getText());
        elec_P = new Double(inputFields.get("elec_P").getText());
        metal_P = new Double(inputFields.get("metal_P").getText());
        metal_BC = new Double(inputFields.get("metal_BC").getText());
        minutes_BC = new Double(inputFields.get("minutes_BC").getText());
        elec_OC = new Double(inputFields.get("elec_OC").getText());
        pop_OC = new Double(inputFields.get("pop_OC").getText());
        health_BS = new Double(inputFields.get("health_BS").getText());
        speed_kmps_BS = new Double(inputFields.get("speed_kmps_BS").getText());
        attack_BS = new Double(inputFields.get("attack_BS").getText());
        armor_type_BS = inputFields.get("armor_type_BS").getText();
        weakness_BS = inputFields.get("weakness_BS").getText();
        strength_BS = inputFields.get("strength_BS").getText();
        modifier_BS = new Double(inputFields.get("modifier_BS").getText());

    }

    public static HashMap<String, TextField> getInputFields(){return inputFields;}
    public static boolean unsavedIndividualRuleChanges = false;
    public static boolean unsavedRulesChanges = false;

    private static String[] columnHeadings = {
        "object_type",
                "food_P",
                "water_P",
                "housing_P",
                "elec_P",
                "metal_P",
                "metal_BC",
                "minutes_BC",
                "elec_OC",
                "pop_OC",
                "health_BS",
                "speed_kmps_BS",
                "attack_BS",
                "armor_type_BS",
                "weakness_BS",
                "strength_BS",
                "modifier_BS"
    };
    public static boolean loadHeadings(String CSVHeadingsLine)
    {
        columnHeadings = CSVHeadingsLine.split(",");
        return true;
    }
    public static TableView<Rule> buildTableView()
    {
        TableView<Rule> tableView = new TableView<Rule>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        for (int i = 0; i < columnHeadings.length; i++)
        {
            if (i == 0 || (i>=13 && i <=15)) {
                //System.out.println("String: " + columnHeadings[i].substring(0,1).toUpperCase()+columnHeadings[i].substring(1));
                TableColumn<Rule, String> c = new TableColumn<Rule, String>(columnHeadings[i].substring(0,1).toUpperCase()+columnHeadings[i].substring(1));
                c.setCellValueFactory(new PropertyValueFactory<Rule,String>(columnHeadings[i].substring(0,1).toUpperCase()+columnHeadings[i].substring(1)));
                c.setId(columnHeadings[i]);
                c.textProperty().addListener((oProp,oldText,newText) -> {
                    unsavedIndividualRuleChanges = true;
                });
                tableView.getColumns().add(c);
            }
            else
            {
                //System.out.println("Double: " + columnHeadings[i].substring(0,1).toUpperCase()+columnHeadings[i].substring(1));
                TableColumn<Rule, Double> c = new TableColumn<Rule, Double>(columnHeadings[i].substring(0,1).toUpperCase()+columnHeadings[i].substring(1));
                c.setCellValueFactory(new PropertyValueFactory<Rule,Double>(columnHeadings[i].substring(0,1).toUpperCase()+columnHeadings[i].substring(1)));
                c.textProperty().addListener((oProp,oldText,newText) -> {
                    unsavedIndividualRuleChanges = true;
                });
                tableView.getColumns().add(c);
            }
        }
        return tableView;
    }
    public static HBox buildTextFields()
    {
        HBox TFContainer = new HBox();
        inputFields = new HashMap<>();
        for (int i = 0; i < columnHeadings.length; i++)
        {

            TextField tempTF = new TextField();
            tempTF.setId(columnHeadings[i]);
            Label tempL = new Label(columnHeadings[i]);
            VBox tempVB = new VBox(tempL,tempTF);
            TFContainer.getChildren().add(tempVB);
            if (i == 0 || (i>=13 && i <=15)) {
                inputFields.put(columnHeadings[i], tempTF);
                //TODO: add custom text based change listener
            }
            else
            {
                inputFields.put(columnHeadings[i], tempTF);
                //TODO: add custom number based change listener
            }
            tempTF.textProperty().addListener((P,O,N) -> unsavedIndividualRuleChanges = true);
        }
        return TFContainer;
    }

    private double id = -1;
    private String object_type = "---";
    private double food_P = 0.0;
    private double water_P = 0.0;
    private double housing_P = 0.0;
    private double elec_P = 0.0;
    private double metal_P = 0.0;
    private double metal_BC = 0.0;
    private double minutes_BC = 0.0;
    private double elec_OC = 0.0;
    private double pop_OC = 0.0;
    private double health_BS = 0.0;
    private double speed_kmps_BS = 0.0;
    private double attack_BS = 0.0;
    private String armor_type_BS = "---";
    private String weakness_BS = "---";
    private String strength_BS = "---";
    private double modifier_BS = 0.0;

    ///* getters and setters
    public String getObject_type(){return object_type;}
    //public void setObject_type(String input){object_type = input;}
    public double getFood_P(){return food_P;}
    //public void setFood_P(double input){food_P = input;}
    public double getWater_P(){return water_P;}
    //public void setWater_P(double input){water_P = input;}
    public double getHousing_P(){return housing_P;}
    //public void setHousing_P(double input){housing_P = input;}
    public double getElec_P(){return elec_P;}
    //public void setElec_P(double input){elec_P = input;}
    public double getMetal_P(){return metal_P;}
    //public void setMetal_P(double input){metal_P = input;}
    public double getMetal_BC(){return metal_BC;}
    //public void setMetal_BC(double input){metal_BC = input;}
    public double getMinutes_BC(){return minutes_BC;}
    //public void setMinutes_BC(double input){minutes_BC = input;}
    public double getElec_OC(){return elec_OC;}
    //public void setElec_OC(double input){elec_OC = input;}
    public double getPop_OC(){return pop_OC;}
    //public void setPop_OC(double input){pop_OC = input;}
    public double getHealth_BS(){return health_BS;}
    //public void setHealth_BS(double input){health_BS = input;}
    public double getSpeed_kmps_BS(){return speed_kmps_BS;}
    //public void setSpeed_kmps_BS(double input){speed_kmps_BS = input;}
    public double getAttack_BS(){return attack_BS;}
    //public void setAttack_BS(double input){attack_BS = input;}
    public String getArmor_type_BS(){return armor_type_BS;}
    //public void setArmor_type_BS(String input){armor_type_BS = input;}
    public String getWeakness_BS(){return weakness_BS;}
    //public void setWeakness_BS(String input){weakness_BS = input;}
    public String getStrength_BS(){return strength_BS;}
    //public void setStrength_BS(String input){strength_BS = input;}
    public double getModifier_BS(){return modifier_BS;}
    //public void setModifier_BS(double input){modifier_BS = input;}

    // */


    public Rule()
    {    }

    public Rule(HashMap<String, Object> values)
    {
        update(values);
    }

    public static String headersToCSV()
    {
        String CSV = "";
        for (int i = 0; i < columnHeadings.length; i++)
        {
            CSV += "," + columnHeadings[i];
        }
        return CSV.substring(1);
    }


    public String toCSV()
    {
        String CSV = object_type + ",";
        CSV += food_P + ",";
        CSV += water_P + ",";
        CSV += housing_P + ",";
        CSV += elec_P + ",";
        CSV += metal_P + ",";
        CSV += metal_BC + ",";
        CSV += minutes_BC + ",";
        CSV += elec_OC + ",";
        CSV += pop_OC + ",";
        CSV += health_BS + ",";
        CSV += speed_kmps_BS + ",";
        CSV += attack_BS + ",";
        CSV += armor_type_BS + ",";
        CSV += weakness_BS + ",";
        CSV += strength_BS + ",";
        CSV += modifier_BS;
        return CSV;
    }

    public String toSQLDelete()
    {
        return "DELETE FROM darkspacerules WHERE ID=" + id;
    }

    public String toSQLInsertOrUpdate()
    {
        if (id < 0) {
            String start = "INSERT INTO darkspacerules ";
            String columns = "(";
            String values = " VALUES (";
            columns += "ObjectName,";
            values += "\"" + object_type + "\",";
            columns += "foodP,";
            values += food_P + ",";
            columns += "waterP,";
            values += water_P + ",";
            columns += "housingP,";
            values += housing_P + ",";
            columns += "elecP,";
            values += elec_P + ",";
            columns += "metalP,";
            values += metal_P + ",";
            columns += "metalBC,";
            values += metal_BC + ",";
            columns += "minutesBC,";
            values += minutes_BC + ",";
            columns += "elecOC,";
            values += elec_OC + ",";
            columns += "popOC,";
            values += pop_OC + ",";
            columns += "healthBS,";
            values += health_BS + ",";
            columns += "speedKmpsBS,";
            values += speed_kmps_BS + ",";
            columns += "attackBS,";
            values += attack_BS + ",";
            columns += "armorTypeBS,";
            values += "\"" + armor_type_BS + "\",";
            columns += "weaknessBS,";
            values += "\"" + weakness_BS + "\",";
            columns += "strengthBS,";
            values += "\"" + strength_BS + "\",";
            columns += "modifireBS)";
            values += modifier_BS + ")";

            return start + columns + values;
        }
        else
        {
            String start = "UPDATE darkspacerules ";
            String values = "SET ";
            values += "ObjectName=";
            values += "\"" + object_type + "\",";
            values += "foodP=";
            values += food_P + ",";
            values += "waterP=";
            values += water_P + ",";
            values += "housingP=";
            values += housing_P + ",";
            values += "elecP=";
            values += elec_P + ",";
            values += "metalP=";
            values += metal_P + ",";
            values += "metalBC=";
            values += metal_BC + ",";
            values += "minutesBC=";
            values += minutes_BC + ",";
            values += "elecOC=";
            values += elec_OC + ",";
            values += "popOC=";
            values += pop_OC + ",";
            values += "healthBS=";
            values += health_BS + ",";
            values += "speedKmpsBS=";
            values += speed_kmps_BS + ",";
            values += "attackBS=";
            values += attack_BS + ",";
            values += "armorTypeBS=";
            values += "\"" + armor_type_BS + "\",";
            values += "weaknessBS=";
            values += "\"" + weakness_BS + "\",";
            values += "strengthBS=";
            values += "\"" + strength_BS + "\",";
            values += "modifireBS=";
            values += modifier_BS + ")";

            return start + values + " WHERE ID=" + id;
        }
    }

    public void update(HashMap<String, Object> CSVValues)
    {
        //String[] StringArrayValues = CSVValues.split(",");

        object_type = (String)CSVValues.get("ObjectName");
        food_P = (Double)CSVValues.get("foodP");
        water_P = (Double)CSVValues.get("waterP");
        housing_P = (Double)CSVValues.get("housingP");
        elec_P = (Double)CSVValues.get("elecP");
        metal_P = (Double)CSVValues.get("metalP");
        metal_BC = (Double)CSVValues.get("metalBC");
        minutes_BC = (Double)CSVValues.get("minutesBC");
        elec_OC = (Double)CSVValues.get("elecOC");
        pop_OC = (Double)CSVValues.get("popOC");
        health_BS = (Double)CSVValues.get("healthBS");
        speed_kmps_BS = (Double)CSVValues.get("speedKmpsBS");
        attack_BS = (Double)CSVValues.get("attackBS");
        armor_type_BS = (String)CSVValues.get("armorTypeBS");
        weakness_BS = (String)CSVValues.get("weaknessBS");
        strength_BS = (String)CSVValues.get("strengthBS");
        modifier_BS = (Double)CSVValues.get("modifireBS");
        id = (Double)CSVValues.get("ID");
    }

}
