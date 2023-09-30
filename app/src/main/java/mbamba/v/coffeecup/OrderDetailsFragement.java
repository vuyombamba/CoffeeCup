package mbamba.v.coffeecup;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

public class OrderDetailsFragement extends Fragment {

    private TextView lblOrderDetails, lblPickUpTime, lblOrderConfirmation, lblDisplayTime, lblGreeting;
    private ImageView imgTime;
    public Button btnConfirmOrder, btnCancelOrder;
    private static final Double mCoffeePrice = 19.95;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_details, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        lblOrderDetails = getActivity().findViewById(R.id.lblOrderDetails);
        lblPickUpTime = getActivity().findViewById(R.id.lblPickupTime);
        btnConfirmOrder = getActivity().findViewById(R.id.btnConfirmOrder);
        lblOrderConfirmation = getActivity().findViewById(R.id.lblOrderConfirmation);
        lblDisplayTime = getActivity().findViewById(R.id.lblDisplayTime);
        imgTime = getActivity().findViewById(R.id.imgTime);
        lblGreeting = getActivity().findViewById(R.id.lblGreeting);
        btnCancelOrder = getActivity().findViewById(R.id.btnCancelOrder);

        lblOrderDetails.append("You have placed an order for " + getActivity().getIntent().getIntExtra("NoOfCups", 0) + " " +
                getActivity().getIntent().getStringExtra("CupSize") + " cups of "+ getActivity().getIntent().getStringExtra("CoffeeType")
                +" " + getActivity().getIntent().getStringExtra("Milk") + " milk and each with " + getActivity().getIntent().getIntExtra("NoOfSugarSpoons", 0)
                + " spoons of sugar.\n"  +"This order will cost you R" +
                String.format("%.2f",getActivity().getIntent().getIntExtra("NoOfCups", 1) * mCoffeePrice) + "\n");
        lblPickUpTime.setText("Select pick-up time: ");

        lblGreeting.append(" " + getActivity().getIntent().getStringExtra("name"));
        btnConfirmOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                lblOrderConfirmation.setText("Your order was placed successfully, kindly pick-up at " + lblDisplayTime.getText());
            }
        });

        imgTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                        .setTimeFormat(TimeFormat.CLOCK_24H)
                        .setHour(07)
                        .setMinute(30)
                        .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
                        .setTitleText("Pick Time")
                        .build();
                timePicker.addOnPositiveButtonClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view) {
                        imgTime.setVisibility(View.GONE);
                        lblDisplayTime.setText(timePicker.getHour() + " : " + timePicker.getMinute());
                    }
                });
                timePicker.show(getFragmentManager(), "tag");
            }
        });

        btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent("mbamba.v.coffeecup.MainActivity"));
                getActivity().finish();
            }
        });
    }



}