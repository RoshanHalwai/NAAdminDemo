package com.kirtanlabs.nammaapartmentssocietyservices.home.timeline;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.kirtanlabs.nammaapartmentssocietyservices.BaseActivity;
import com.kirtanlabs.nammaapartmentssocietyservices.Constants;
import com.kirtanlabs.nammaapartmentssocietyservices.R;
import com.kirtanlabs.nammaapartmentssocietyservices.SocietyServiceGlobal;
import com.kirtanlabs.nammaapartmentssocietyservices.login.OTP;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;
import static com.kirtanlabs.nammaapartmentssocietyservices.Constants.ALL_SOCIETYSERVICENOTIFICATION_REFERENCE;
import static com.kirtanlabs.nammaapartmentssocietyservices.Constants.END_SERVICE_REQUEST_CODE;
import static com.kirtanlabs.nammaapartmentssocietyservices.Constants.FIREBASE_CHILD_ACCEPTED;
import static com.kirtanlabs.nammaapartmentssocietyservices.Constants.FIREBASE_CHILD_COMPLETED;
import static com.kirtanlabs.nammaapartmentssocietyservices.Constants.FIREBASE_CHILD_STATUS;
import static com.kirtanlabs.nammaapartmentssocietyservices.SocietyServiceGlobal.societyServiceUID;
import static com.kirtanlabs.nammaapartmentssocietyservices.Utilities.capitalizeString;

public class ServingFragment extends Fragment implements View.OnClickListener {

    /* ------------------------------------------------------------- *
     * Private Members
     * ------------------------------------------------------------- */

    private BaseActivity baseActivity;
    private RelativeLayout layoutAwaitingResponse, layoutAcceptedUserDetails;
    private TextView textResidentNameValue;
    private TextView textApartmentValue;
    private TextView textFlatNumberValue;
    private TextView textTimeSlotValue;
    private TextView textProblemDescriptionValue;
    private TextView textServiceTypeValue;
    private String notificationUID;
    private String mobileNumber;
    private String endOTP;

    /* ------------------------------------------------------------- *
     * Overriding Fragment Objects
     * ------------------------------------------------------------- */

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_serving, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*Getting Id's for all the views*/
        layoutAwaitingResponse = view.findViewById(R.id.layoutAwaitingResponse);
        layoutAcceptedUserDetails = view.findViewById(R.id.layoutAcceptedUserDetails);
        TextView textAwaitingResponse = view.findViewById(R.id.textAwaitingResponse);
        TextView textResidentName = view.findViewById(R.id.textResidentName);
        TextView textApartment = view.findViewById(R.id.textApartment);
        TextView textFlatNumber = view.findViewById(R.id.textFlatNumber);
        TextView textServiceType = view.findViewById(R.id.textServiceType);
        TextView textTimeSlot = view.findViewById(R.id.textTimeSlot);
        TextView textProblemDescription = view.findViewById(R.id.textProblemDescription);
        textResidentNameValue = view.findViewById(R.id.textResidentNameValue);
        textApartmentValue = view.findViewById(R.id.textApartmentValue);
        textFlatNumberValue = view.findViewById(R.id.textFlatNumberValue);
        textServiceTypeValue = view.findViewById(R.id.textServiceTypeValue);
        textTimeSlotValue = view.findViewById(R.id.textTimeSlotValue);
        textProblemDescriptionValue = view.findViewById(R.id.textProblemDescriptionValue);
        Button buttonCallResident = view.findViewById(R.id.buttonCallResident);
        Button buttonEndService = view.findViewById(R.id.buttonEndService);

        buttonCallResident.setVisibility(View.VISIBLE);

        /*Setting font for all the views*/
        textAwaitingResponse.setTypeface(Constants.setLatoRegularFont(Objects.requireNonNull(getActivity())));
        textResidentName.setTypeface(Constants.setLatoRegularFont(Objects.requireNonNull(getActivity())));
        textApartment.setTypeface(Constants.setLatoRegularFont(Objects.requireNonNull(getActivity())));
        textFlatNumber.setTypeface(Constants.setLatoRegularFont(Objects.requireNonNull(getActivity())));
        textServiceType.setTypeface(Constants.setLatoRegularFont(Objects.requireNonNull(getActivity())));
        textTimeSlot.setTypeface(Constants.setLatoRegularFont(Objects.requireNonNull(getActivity())));
        textProblemDescription.setTypeface(Constants.setLatoRegularFont(Objects.requireNonNull(getActivity())));
        textResidentNameValue.setTypeface(Constants.setLatoBoldFont(Objects.requireNonNull(getActivity())));
        textApartmentValue.setTypeface(Constants.setLatoBoldFont(Objects.requireNonNull(getActivity())));
        textFlatNumberValue.setTypeface(Constants.setLatoBoldFont(Objects.requireNonNull(getActivity())));
        textServiceTypeValue.setTypeface(Constants.setLatoBoldFont(Objects.requireNonNull(getActivity())));
        textTimeSlotValue.setTypeface(Constants.setLatoBoldFont(Objects.requireNonNull(getActivity())));
        textProblemDescriptionValue.setTypeface(Constants.setLatoBoldFont(Objects.requireNonNull(getActivity())));
        buttonCallResident.setTypeface(Constants.setLatoLightFont(Objects.requireNonNull(getActivity())));
        buttonEndService.setTypeface(Constants.setLatoLightFont(Objects.requireNonNull(getActivity())));

        baseActivity = (BaseActivity) getActivity();

        /*Setting listener for item in card view*/
        buttonCallResident.setOnClickListener(this);
        buttonEndService.setOnClickListener(this);

        updateUIWithServingData();
    }


    /* ------------------------------------------------------------- *
     * Overriding onActivity Result
     * ------------------------------------------------------------- */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == END_SERVICE_REQUEST_CODE && resultCode == RESULT_OK) {
            setSocietyServiceWorkStatus();

            /*This method is used to create a dialog that indicates society service have successfully completed its service*/
            openServiceSuccessfullyCompletedDialog();
        }
    }

    /* ------------------------------------------------------------- *
     * Overriding OnClick Listeners
     * ------------------------------------------------------------- */

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonCallResident:
                baseActivity.makePhoneCall(mobileNumber);
                break;
            case R.id.buttonEndService:
                Intent intent = new Intent(getActivity(), OTP.class);
                intent.putExtra(Constants.SCREEN_TITLE, R.string.serving);
                intent.putExtra(Constants.END_OTP, endOTP);
                startActivityForResult(intent, END_SERVICE_REQUEST_CODE);
                break;
        }
    }

    /**
     * This method is invoked when a Society Service presses 'End Service' button and user
     * authenticates OTP successfully
     */
    private void setSocietyServiceWorkStatus() {
        /*Getting the reference to change the 'status' of the Society Service to 'completed' once the service ends*/
        DatabaseReference workStatusReference = ALL_SOCIETYSERVICENOTIFICATION_REFERENCE.child(notificationUID);
        workStatusReference.child(FIREBASE_CHILD_STATUS).setValue(FIREBASE_CHILD_COMPLETED);

        /*Remove the Notification UID under Serving and put them under History*/
        ((SocietyServiceGlobal) Objects.requireNonNull(getActivity()).getApplicationContext())
                .getServingNotificationReference()
                .child(notificationUID)
                .removeValue((databaseError, databaseReference) ->
                        ((SocietyServiceGlobal) getActivity().getApplicationContext())
                                .getHistoryNotificationReference()
                                .child(notificationUID)
                                .setValue(FIREBASE_CHILD_ACCEPTED).addOnSuccessListener(aVoid -> updateUIWithServingData()));

    }

    /*-------------------------------------------------------------------------------
     *Private Method
     *-----------------------------------------------------------------------------*/

    private void updateUIWithServingData() {
        RetrievingNotificationData retrievingNotificationData = new RetrievingNotificationData(getActivity(), societyServiceUID);
        retrievingNotificationData.getServingNotificationData(societyServiceNotification -> {
            if (societyServiceNotification != null) {
                endOTP = societyServiceNotification.getEndOTP();
                textResidentNameValue.setText(societyServiceNotification.getNaUser().getPersonalDetails().getFullName());
                textApartmentValue.setText(societyServiceNotification.getNaUser().getFlatDetails().getApartmentName());
                textFlatNumberValue.setText(societyServiceNotification.getNaUser().getFlatDetails().getFlatNumber());
                textServiceTypeValue.setText(capitalizeString(societyServiceNotification.getSocietyServiceType()));
                textTimeSlotValue.setText(societyServiceNotification.getTimeSlot());
                textProblemDescriptionValue.setText(societyServiceNotification.getProblem());
                layoutAwaitingResponse.setVisibility(View.GONE);
                layoutAcceptedUserDetails.setVisibility(View.VISIBLE);

                /*Getting UID of notification*/
                notificationUID = societyServiceNotification.getNotificationUID();
                /*Getting the mobile number of user*/
                mobileNumber = societyServiceNotification.getNaUser().getPersonalDetails().getPhoneNumber();
            } else {
                layoutAwaitingResponse.setVisibility(View.VISIBLE);
                layoutAcceptedUserDetails.setVisibility(View.GONE);
            }
        });
    }

    /**
     * This method is used to open a dialog when society service successfully completes a service.
     */
    private void openServiceSuccessfullyCompletedDialog() {
        View successfulDialog = View.inflate(getActivity(), R.layout.layout_successful_dialog, null);

        /*Getting Id's for all the views*/
        TextView textDescription = successfulDialog.findViewById(R.id.textDescription);

        /*Setting font for all the views*/
        textDescription.setTypeface(Constants.setLatoBoldFont(Objects.requireNonNull(getActivity())));

        /*Setting view in Dialog*/
        AlertDialog.Builder alertSuccessfulDialog = new AlertDialog.Builder(getActivity());
        alertSuccessfulDialog.setCancelable(false);
        alertSuccessfulDialog.setPositiveButton(R.string.ok, (dialog, which) -> dialog.cancel());
        alertSuccessfulDialog.setView(successfulDialog);

        AlertDialog dialog = alertSuccessfulDialog.create();

        new Dialog(getActivity());
        dialog.show();
    }
}
