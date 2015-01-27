package co.mobilemakers.sandwichstudio;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import java.util.EnumSet;


public class MainActivity extends ActionBarActivity {

    final static public String KEY_BREAD = "bread";
    final static public String KEY_TOPPINGS = "toppings";

    public enum BreadEnum {
        WHEAT,
        WHITE,
        RYE
    }

    public enum ToppingEnum {
        TOMATO,
        LATTUCE,
        ONION,
        CARROT,
        SESAME,
        OLIVES,
        HAM,
        CHEESE
    }

    Button mButtonPlaceOrder;

    RadioButton mRadioWheat, mRadioWhite, mRadioRye;
    CheckBox mCheckTomato, mCheckLattuce, mCheckOnion, mCheckCarrot,
        mCheckSesame, mCheckOlives, mCheckHam, mCheckCheese;

    protected class OrderButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, OrderConfirmationActivity.class);

            BreadEnum breadType;

            if (mRadioWheat.isChecked()) {
                breadType = BreadEnum.WHEAT;
            }
            else if (mRadioWhite.isChecked()) {
                breadType = BreadEnum.WHITE;
            }
            else /*if (mRadioRye.isChecked())*/ {
                breadType = BreadEnum.RYE;
            }

            EnumSet<ToppingEnum> toppingsSet = EnumSet.noneOf(ToppingEnum.class);

            if (mCheckTomato.isChecked()) {
                toppingsSet.add(ToppingEnum.TOMATO);
            }
            else if (mCheckLattuce.isChecked()) {
                toppingsSet.add(ToppingEnum.LATTUCE);
            }
            else if (mCheckOnion.isChecked()) {
                toppingsSet.add(ToppingEnum.ONION);
            }
            else if (mCheckCarrot.isChecked()) {
                toppingsSet.add(ToppingEnum.CARROT);
            }
            else if (mCheckOlives.isChecked()) {
                toppingsSet.add(ToppingEnum.OLIVES);
            }
            else if (mCheckSesame.isChecked()) {
                toppingsSet.add(ToppingEnum.SESAME);
            }
            else if (mCheckHam.isChecked()) {
                toppingsSet.add(ToppingEnum.HAM);
            }
            else if (mCheckCheese.isChecked()) {
                toppingsSet.add(ToppingEnum.CHEESE);
            }

            intent.putExtra(KEY_BREAD, breadType);
            intent.putExtra(KEY_TOPPINGS, toppingsSet);
            startActivity(intent);
        }
    }

    protected class RadioBreadListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            enableOrderIfBread();
        }
    }

    protected void enableOrderIfBread() {
        mButtonPlaceOrder.setEnabled(mRadioWheat.isChecked() ||
                mRadioWhite.isChecked() || mRadioRye.isChecked());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controlsToVars();
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();

        enableOrderIfBread();
    }

    private void controlsToVars() {
        mButtonPlaceOrder = (Button)findViewById(R.id.button_place_order);

        mRadioWheat = (RadioButton)findViewById(R.id.radio_bread_wheat);
        mRadioWhite = (RadioButton)findViewById(R.id.radio_bread_white);
        mRadioRye = (RadioButton)findViewById(R.id.radio_bread_rye);

        mCheckTomato = (CheckBox)findViewById(R.id.check_topping_tomato);
        mCheckLattuce = (CheckBox)findViewById(R.id.check_topping_lattuce);
        mCheckOnion = (CheckBox)findViewById(R.id.check_topping_onion);
        mCheckCarrot = (CheckBox)findViewById(R.id.check_topping_carrot);
        mCheckOlives = (CheckBox)findViewById(R.id.check_topping_olives);
        mCheckSesame = (CheckBox)findViewById(R.id.check_topping_sesame);
        mCheckHam = (CheckBox)findViewById(R.id.check_topping_ham);
        mCheckCheese = (CheckBox)findViewById(R.id.check_topping_cheese);

        enableOrderIfBread();
    }

    private void setListeners() {
        mButtonPlaceOrder.setOnClickListener(new OrderButtonListener());

        RadioBreadListener radioListener = new RadioBreadListener();

        mRadioWheat.setOnClickListener(radioListener);
        mRadioWhite.setOnClickListener(radioListener);
        mRadioRye.setOnClickListener(radioListener);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
