package mbamba.v.coffeecup;

import static android.os.ParcelFileDescriptor.MODE_WORLD_READABLE;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStreamReader;


public class CoffeeOrderDialogFragment extends Fragment {

    private ImageButton downloadIcon;
    private Context mContext;
    TextView lblCoffeeType, lblMilk, lblSugar, lblCups;
    String sOrder = "";

    private final static int READ_BLOCK_SIZE = 100;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lblCoffeeType = getActivity().findViewById(R.id.lblCoffee);
        lblMilk = getActivity().findViewById(R.id.lblMilk);
        lblSugar = getActivity().findViewById(R.id.lblSugar);
        lblCups = getActivity().findViewById(R.id.lblCups);
        downloadIcon = getActivity().findViewById(R.id.btnDownloadIcon);

        sOrder += "Coffee: " + lblCoffeeType.toString() + "\nMilk: " + lblMilk.toString() + "\nNumber of Cups: " +
                    lblCups.toString() + "\nSugar: " + lblSugar.toString();
        downloadIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog show;
                show = new Builder(getActivity())
                        .setCancelable(true)
                        .setTitle("Confirm order file download!")
                        .setMessage(sOrder)
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                               try
                               {
                                   File sdCard = Environment.getExternalStorageDirectory();
                                   File directoru = new File(sdCard.getAbsolutePath() + "/MyFiles");
                                   directoru.mkdirs();
                                   File file = new File(directoru, "order.txt");
                                   FileInputStream fIn = new FileInputStream(file);
                                   InputStreamReader isr = new InputStreamReader(fIn);
                                    char[] inputBuffer = new char[READ_BLOCK_SIZE];
                                    String s = "";
                                    int charRead;

                                    while((charRead = isr.read(inputBuffer)) > 0)
                                    {
                                        String readString = String.copyValueOf(inputBuffer, 0, charRead);
                                        s += readString;
                                        inputBuffer = new char[READ_BLOCK_SIZE];
                                    }
                                   sOrder = "";
                                    Toast.makeText(getActivity().getBaseContext(), "Order file downloaded successfully!", Toast.LENGTH_SHORT).show();
                               }catch (IOException ioe)
                               {
                                    ioe.printStackTrace();
                               }

                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity().getBaseContext(), "Order file download Cancelled", Toast.LENGTH_SHORT).show();

                            }
                        }).show();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_details, container, false);
    }

}