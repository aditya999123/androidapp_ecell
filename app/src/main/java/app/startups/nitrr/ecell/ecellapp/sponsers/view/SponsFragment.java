package app.startups.nitrr.ecell.ecellapp.sponsers.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import app.startups.nitrr.ecell.ecellapp.R;
import app.startups.nitrr.ecell.ecellapp.sponsers.model.RetrofitSponsProvider;
import app.startups.nitrr.ecell.ecellapp.sponsers.presenter.SponsPresenter;
import app.startups.nitrr.ecell.ecellapp.sponsers.presenter.SponsPresenterImpl;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SponsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SponsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SponsFragment extends Fragment implements SponsInterface {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @BindView(R.id.recycler_view_spons)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar_spons)
    ProgressBar progressBar;
    private SponsPresenter sponsPresenter;
    private SponsAdapter adapter;
    private GridLayoutManager lLayout;

    private OnFragmentInteractionListener mListener;

    public SponsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SponsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SponsFragment newInstance(String param1, String param2) {
        SponsFragment fragment = new SponsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_spons, container, false);

        ButterKnife.bind(this,view);
        sponsPresenter=new SponsPresenterImpl(this,new RetrofitSponsProvider());

        adapter = new SponsAdapter(getContext());

        lLayout = new GridLayoutManager(getContext(),2);

        recyclerView.setLayoutManager(lLayout);
        recyclerView.setAdapter(adapter);
        sponsPresenter.requestSpons();


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    @Override
    public void showLoading(boolean show) {
        if(show)
        {
            progressBar.setVisibility(View.VISIBLE);
        }
        else
            progressBar.setVisibility(View.GONE);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getActivity(), ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(List<SponsData> sponsDataList) {
        adapter.setData(sponsDataList);
        adapter.notifyDataSetChanged();
    }
}
