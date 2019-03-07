package ee.android.reneroost.isiklikprojekt.KRIS;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HalduriValikFragment extends Fragment {

    private static final String SILT = "HalduriValikFragment";

    @Override
    public View onCreateView(LayoutInflater taispuhuja, ViewGroup konteiner, Bundle savedInstanceState) {

        RecyclerView halduriTaaskasutaja = (RecyclerView) taispuhuja.inflate(
                R.layout.fragment_rolli_valimine, konteiner, false);

        String[] halduriteNimed = new String[Haldurid.saa(getActivity()).saaHaldurid().size()];
        for(int i = 0; i < halduriteNimed.length; i++) {
            halduriteNimed[i] = Haldurid.saa(getActivity()).saaHaldurid().get(i).saaNimi();
        }

        int[] halduritePildid = new int[Haldurid.saa(getActivity()).saaHaldurid().size()];
        for(int i = 0; i < halduritePildid.length; i++) {
            halduritePildid[i] = Haldurid.saa(getActivity()).saaHaldurid().get(i).saaPildiRessursiId();
        }

        KasutajaKirjeldusAdapter adapter = new KasutajaKirjeldusAdapter(halduriteNimed, halduritePildid);
        halduriTaaskasutaja.setAdapter(adapter);

        LinearLayoutManager paigutuseHaldur = new LinearLayoutManager(getActivity());
        halduriTaaskasutaja.setLayoutManager(paigutuseHaldur);

        adapter.maaraListener(new KasutajaKirjeldusAdapter.Listener() {
            @Override
            public void onClick(int positsioon) {
                getActivity().finish();
            }
        });

        return halduriTaaskasutaja;
    }



/*
    private RecyclerView mRolliValikTaaskasutajaVaade;
    private RolliAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater taispuhuja, ViewGroup konteiner, Bundle savedInstanceState) {
        View vaade = taispuhuja.inflate(R.layout.fragment_rolli_valimine, konteiner, false);

        mRolliValikTaaskasutajaVaade = (RecyclerView) vaade.findViewById(R.id.roll_taaskasutaja_vaade);
        mRolliValikTaaskasutajaVaade.setLayoutManager(new LinearLayoutManager(getActivity()));

        uuendaUI();

        Log.w(SILT, "fragment kaivitus");
        return vaade;
    }

    private void uuendaUI() {
        Haldurid haldurid = Haldurid.saa(getActivity());
        List<Haldur> halduriteNimekiri = haldurid.saaHaldurid();

        mAdapter = new RolliAdapter(halduriteNimekiri);
        mRolliValikTaaskasutajaVaade.setAdapter(mAdapter);
    }

    private class RolliHoidja extends RecyclerView.ViewHolder {

        private Haldur mHaldur;

        private TextView mNimiTekstivaade;
        private TextView mRollTekstivaade;

        public RolliHoidja(LayoutInflater taispuhuja, ViewGroup vanem) {
            super(taispuhuja.inflate(R.layout.nimekirja_uksus_roll, vanem, false));

            mNimiTekstivaade = (TextView) itemView.findViewById(R.id.kasutaja_nimi_tekstivaade);
            mRollTekstivaade = (TextView) itemView.findViewById(R.id.kasutaja_roll_tekstivaade);
        }

        public void seo(Haldur haldur) {
            mHaldur = haldur;
            mNimiTekstivaade.setText(mHaldur.saaNimi());
            mRollTekstivaade.setText(mHaldur.saaRoll());
        }
    }

    private class RolliAdapter extends RecyclerView.Adapter<RolliHoidja> {
        private List<Haldur> mHaldurid;

        public RolliAdapter(List<Haldur> haldurid) {
            mHaldurid = haldurid;
        }


        @NonNull
        @Override
        public RolliHoidja onCreateViewHolder(@NonNull ViewGroup vanem, int i) {
            LayoutInflater paigutuseTaispuhuja = LayoutInflater.from(getActivity());

            return new RolliHoidja(paigutuseTaispuhuja, vanem);
        }

        @Override
        public void onBindViewHolder(@NonNull RolliHoidja rolliHoidja, int positsioon) {
            Haldur haldur = mHaldurid.get(positsioon);
            rolliHoidja.seo(haldur);
        }

        @Override
        public int getItemCount() {
            return mHaldurid.size();
        }
    }
    */
}
