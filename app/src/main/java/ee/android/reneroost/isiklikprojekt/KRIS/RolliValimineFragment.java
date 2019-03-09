package ee.android.reneroost.isiklikprojekt.KRIS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class RolliValimineFragment extends Fragment {

    private static final String SILT = "RolliValimineFragment";
    public static final String EKSTRA_ROLLI_NIMI = "ee.android.reneroost.isiklikprojekt.KRIS.rolli_nimi";
    public static final String EKSTRA_ROLLI_ID = "ee.android.reneroost.isiklikprojekt.KRIS.rolli_id";

    private static final String ARGUMENT_VALITUD_AMET = "valitud_amet";
    private int valitudAmet;

    KasutajadSingleton kasutajadSingleton = KasutajadSingleton.saaInstants(getContext());
    private List<Kasutaja> kasutajad = kasutajadSingleton.saaKasutajad();

    String[] halduriteNimed;
    int[] halduritePildid;

    public static Fragment uusInstants(int amet) {
        Bundle argmendid = new Bundle();
        argmendid.putSerializable(ARGUMENT_VALITUD_AMET, amet);
        RolliValimineFragment fragment = new RolliValimineFragment();
        fragment.setArguments(argmendid);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        valitudAmet = getArguments().getInt(ARGUMENT_VALITUD_AMET); }

    @Override
    public View onCreateView(LayoutInflater taispuhuja, ViewGroup konteiner, Bundle savedInstanceState) {

        RecyclerView halduriTaaskasutaja = (RecyclerView) taispuhuja.inflate(
                R.layout.fragment_rolli_valimine, konteiner, false);

        Kasutaja kasutaja;
        int antudAmetigaKasutajateHulk =
                kasutajadSingleton.saaAntudAmetigaKasutajateHulk(valitudAmet);
        halduriteNimed = new String[antudAmetigaKasutajateHulk];
        halduritePildid = new int[antudAmetigaKasutajateHulk];
        for(int i = 0, j = 0; i < kasutajad.size(); i++) {
            kasutaja = kasutajad.get(i);
            if (kasutaja.saaAmet() == valitudAmet) {
                halduriteNimed[j] = kasutaja.saaNimi();
                halduritePildid[j] = kasutaja.saaPildiRessursiId();
                j++;
            }
        }

        KasutajaKirjeldusAdapter adapter = new KasutajaKirjeldusAdapter(halduriteNimed, halduritePildid);
        halduriTaaskasutaja.setAdapter(adapter);

        LinearLayoutManager paigutuseHaldur = new LinearLayoutManager(getActivity());
        halduriTaaskasutaja.setLayoutManager(paigutuseHaldur);

        adapter.maaraListener(new KasutajaKirjeldusAdapter.Listener() {
            @Override
            public void onClick(int positsioon) {

                Toast rost = Toast.makeText(getContext(), getResources().getString(R.string.valisid_uue_kasutaja), Toast.LENGTH_SHORT);
                rost.show();
                Intent andmed = new Intent();

                int kasutajaId = 0;
                for(int i = 0; i < kasutajad.size(); i++) {
                    if (kasutajad.get(i).saaNimi().equals(halduriteNimed[positsioon])) {
                        kasutajaId = i;
                    }
                }

                andmed.putExtra(EKSTRA_ROLLI_ID, kasutajaId);
                Objects.requireNonNull(getActivity()).setResult(Activity.RESULT_OK, andmed);
                getActivity().finish();
            }
        });

        return halduriTaaskasutaja;
    }

}
