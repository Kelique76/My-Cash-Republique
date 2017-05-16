package com.kelique.firewithfirebaseapp;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by kelique on 4/14/2017.
 */

class AnggotaCustom extends RecyclerView.Adapter<AnggotaCustom.MyHolder>{
    MainActivity c;
    ArrayList<Anggota2> d;
    RecyclerView r;


    public AnggotaCustom(MainActivity mainActivity, ArrayList<Anggota2> data, RecyclerView recycler) {
        c = mainActivity;
        d = data;
        r= recycler;


    }
    View.OnClickListener listv = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = r.getChildLayoutPosition(v);

            final Dialog dialog = new Dialog(c);
            dialog.setContentView(R.layout.activity_tambah_aktifitas);

            final Anggota2 b=d.get(position);


            dialog.setTitle("EDIT ANGGOTA");
            dialog.setContentView(R.layout.activity_tambah_aktifitas);

            final EditText name = (EditText) dialog.findViewById(R.id.textView2);
            final EditText alamat = (EditText) dialog.findViewById(R.id.textView3);
            final EditText ktp = (EditText) dialog.findViewById(R.id.textView4);
            final EditText hp = (EditText) dialog.findViewById(R.id.textView5);
            Button btn = (Button) dialog.findViewById(R.id.button2);
            btn.setText("Rubah Data");

            name.setText(b.getNamaanggota());
            alamat.setText(b.getAlamatanggota());
            ktp.setText(b.getKTPanggota());
            hp.setText(b.getHPanggota());

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        DatabaseReference satu = FirebaseDatabase.getInstance().getReference("Anggota Dagang");
                        satu.child(b.getKeyanggota()).child("namaanggota").setValue(name.getText().toString());
                        satu.child(b.getKeyanggota()).child("alamatanggota").setValue(alamat.getText().toString());
                        satu.child(b.getKeyanggota()).child("ktpanggota").setValue(ktp.getText().toString());
                        satu.child(b.getKeyanggota()).child("hpanggota").setValue(hp.getText().toString());

                        //untuk menghapus
                        //satu.child(b.getKeyanggota()).removeValue();
                         //satu.child(b.getKeyanggota()).child("namabarang").removeValue();


                        Toast.makeText(c, "Perubahan Data Berhasil", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();



                }
            });

            dialog.show();

        }
    };

    @Override
    public AnggotaCustom.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.list_item,parent,false);
        v.setOnClickListener(listv);

       // MyHolder b = new MyHolder(v);


        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(AnggotaCustom.MyHolder holder, int position) {
        holder.txtnama.setText(d.get(position).getNamaanggota());
        holder.txtalamat.setText(d.get(position).getAlamatanggota());
        holder.txtktp.setText(d.get(position).getKTPanggota());
        holder.txthp.setText(d.get(position).getHPanggota());

    }

    @Override
    public int getItemCount() {
        return d.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView txtnama, txtalamat, txtktp, txthp;
        public MyHolder(View itemView) {
            super(itemView);
            txtnama = (TextView) itemView.findViewById(R.id.textView6);
            txtalamat=(TextView)itemView.findViewById(R.id.textView7);
            txtktp=(TextView) itemView.findViewById(R.id.textView8);
            txthp=(TextView) itemView.findViewById(R.id.textView9);

        }
    }
}
