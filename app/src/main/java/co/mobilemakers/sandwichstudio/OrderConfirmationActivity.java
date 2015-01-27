package co.mobilemakers.sandwichstudio;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.EnumSet;


public class OrderConfirmationActivity extends ActionBarActivity {

    TextView mTextOrderResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);

        controlsToVars();

        String textResult = extractOrderData();

        showOrderData(textResult);

    }

    private void showOrderData(String textResult) {
        mTextOrderResume.setText(textResult);
    }

    private String extractOrderData() {
        MainActivity.BreadEnum breadType;
        EnumSet<MainActivity.ToppingEnum> toppingsList;

        breadType =
                (MainActivity.BreadEnum) getIntent().getSerializableExtra(MainActivity.KEY_BREAD);
        toppingsList =
                (EnumSet<MainActivity.ToppingEnum>) getIntent().getSerializableExtra(MainActivity.KEY_TOPPINGS);

        String textResult = "";
        textResult += getResources().getString(R.string.order_resume_begin);
        textResult += getResources().getString(R.string.order_resume_bread_begin);
        if (breadType == MainActivity.BreadEnum.WHEAT) {
            textResult += getResources().getString(R.string.wheat);
        }
        else if (breadType == MainActivity.BreadEnum.WHITE) {
            textResult += getResources().getString(R.string.white);
        }
        else if (breadType == MainActivity.BreadEnum.RYE) {
            textResult += getResources().getString(R.string.rye);
        }
        textResult += getResources().getString(R.string.order_resume_bread_end);
        textResult += getResources().getString(R.string.order_resume_topping_begin);
        if (toppingsList.isEmpty()) {
            textResult += getResources().getString(R.string.no_topping);
        }
        else {
            int topsQuantity = toppingsList.size();
            for (MainActivity.ToppingEnum topping : toppingsList) {
                if (topping == MainActivity.ToppingEnum.TOMATO) {
                    textResult += getResources().getString(R.string.tomato);
                }
                else if (topping == MainActivity.ToppingEnum.LATTUCE) {
                    textResult += getResources().getString(R.string.lattuce);
                }
                else if (topping == MainActivity.ToppingEnum.ONION) {
                    textResult += getResources().getString(R.string.onion);
                }
                else if (topping == MainActivity.ToppingEnum.CARROT) {
                    textResult += getResources().getString(R.string.grated_carrot);
                }
                else if (topping == MainActivity.ToppingEnum.OLIVES) {
                    textResult += getResources().getString(R.string.olives);
                }
                else if (topping == MainActivity.ToppingEnum.SESAME) {
                    textResult += getResources().getString(R.string.sesame_seeds);
                }
                else if (topping == MainActivity.ToppingEnum.HAM) {
                    textResult += getResources().getString(R.string.ham);
                }
                else if (topping == MainActivity.ToppingEnum.CHEESE) {
                    textResult += getResources().getString(R.string.cheese);
                }

                if (topsQuantity > 2) {
                    textResult += ", ";
                }
                else if (topsQuantity == 2) {
                    textResult += getResources().getString(R.string.order_resume_topping_and);
                }

                topsQuantity--;
            }
        }

        textResult += getResources().getString(R.string.order_resume_topping_end);
        return textResult;
    }

    private void controlsToVars() {
        mTextOrderResume = (TextView)findViewById(R.id.text_order_resume_text);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_confirmation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
