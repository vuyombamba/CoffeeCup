package mbamba.v.coffeecup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class OrderConfirmationFragement extends Fragment {
    private ListView lstCoffee;
    private ArrayList<Coffee> mCoffeeList;
    private CoffeeAdapter mCoffeeAdapter;
    @Override
    public void onStart()
    {
        super.onStart();
        lstCoffee = getActivity().findViewById(R.id.lstOrder);
        mCoffeeList = new ArrayList();
        mCoffeeList.add(new
                Coffee(getActivity().getIntent().getStringExtra("order"),
                getActivity().getIntent().getStringExtra("Milk"),
                getActivity().getIntent().getIntExtra("NoOfCups", 1),
                getActivity().getIntent().getIntExtra("NoOfSugarSpoons",
                        0)));
        int id = getActivity().getIntent().getIntExtra("id", 0);
        mCoffeeAdapter = new CoffeeAdapter(getActivity(), mCoffeeList, id);
        lstCoffee.setAdapter(mCoffeeAdapter);
    }
}