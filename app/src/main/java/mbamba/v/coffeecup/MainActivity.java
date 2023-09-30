package mbamba.v.coffeecup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText txtName, txtNoOfCups, txtMilk, txtSugar;
    private Button btnPlaceOrder;
    private ImageSwitcher imgSwitcher;
    private ImageView imgRemoveCups, imgAddCups, imgRemoveSugar, imgAddSugar;

    private Spinner ddlMilk;
    private RadioGroup radgrpSize;
    private TextView lblCoffeeType;
    private String sSize = "";
    private int id = 0, iCups = 0, iSugar = 0;


    private Animation next_in, next_out, prev_in, prev_out;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgSwitcher = findViewById(R.id.imgSwitcher);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        next_in = AnimationUtils.loadAnimation(this, R.anim.next_in);
        next_out = AnimationUtils.loadAnimation(this, R.anim.next_out);
        prev_in = AnimationUtils.loadAnimation(this, R.anim.prev_in);
        prev_out = AnimationUtils.loadAnimation(this, R.anim.prev_out);

        Bundle orderDetailsInfo = new Bundle();

        String[] sAnswerMilk = {
                "Yes",
                "No"
        };
        imgRemoveCups = findViewById(R.id.imgRemoveCups);
        imgAddCups = findViewById(R.id.imgAddCups);
        imgRemoveSugar = findViewById(R.id.imgRemoveSugar);
        imgAddSugar = findViewById(R.id.imgAddSugar);
        radgrpSize = findViewById(R.id.radgrpSize);
        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_selectable_list_item, sAnswerMilk);
        ddlMilk.setAdapter(adapter);
        ddlMilk.setSelection(0);

        radgrpSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radSmall = findViewById(R.id.radSmall);
                RadioButton radMedium = findViewById(R.id.radMedium);
                RadioButton radLarge = findViewById(R.id.radLarge);
                if (radSmall.isChecked()) {
                    sSize = "small";
                } else if (radMedium.isChecked()) {
                    sSize = "medium";
                } else if (radLarge.isChecked()) {
                    sSize = "large";
                } else {
                    Toast.makeText(MainActivity.this, "Please select size!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        orderDetailsInfo.putString("size", sSize);
        orderDetailsInfo.putInt("id", id);
        iCups = Integer.parseInt(txtNoOfCups.getText().toString());
        iSugar = Integer.parseInt(txtSugar.getText().toString());
        imgRemoveCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iCups == 1) {
                    iCups = 1;
                } else {
                    iCups--;
                }
                txtNoOfCups.setText(String.valueOf(iCups));
            }
        });
        imgAddCups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iCups == 10) {
                    iCups = 10;
                } else {
                    iCups++;
                }
                txtNoOfCups.setText(String.valueOf(iCups));
            }
        });
        imgRemoveSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iSugar == 1) {
                    iSugar = 1;
                } else {
                    iSugar--;
                }
                txtSugar.setText(String.valueOf(iSugar));
            }
        });
        imgAddSugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iSugar == 3) {
                    iSugar = 3;
                } else {
                    iSugar++;
                }
                txtSugar.setText(String.valueOf(iSugar));
            }
        });
        btnPlaceOrder.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                Intent orderDetailsIntent = new Intent("mbamba.v.coffeecup.OrderDetails");

                orderDetailsInfo.putString("name", txtName.getText().toString());
                orderDetailsInfo.putInt("NoOfCups", Integer.parseInt(txtNoOfCups.getText().toString()));
                orderDetailsInfo.putString("Milk", txtMilk.getText().toString());
                orderDetailsInfo.putInt("NoOfSugarSpoons", Integer.parseInt(txtSugar.getText().toString()));
                orderDetailsInfo.putString("CupSize", sSize);
                orderDetailsIntent.putExtras(orderDetailsInfo);
                startActivity(orderDetailsIntent);

            }
        });
    }

    private void changeName(int _id) {
        String sName = "";
        switch (_id) {
            case 0:
                sName = "Black Coffee";
                break;
            case 1:
                sName = "Ice Coffee";
                break;
            case 2:
                sName = "Espresso";
                break;
            case 3:
                sName = "Latte";
                break;
            default:
                break;
        }
        lblCoffeeType.setText(sName);
    }


}
