package com.sentinel.sentinel.service;

import com.sentinel.sentinel.model.GuidanceResponse;
import com.sentinel.sentinel.model.IncidentCategory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuidanceService {

    public GuidanceResponse getGuidance(IncidentCategory category) {

        return switch (category) {

            case ACCIDENT -> new GuidanceResponse(
                    "CRITICAL",
                    "112",
                    "Emergency Response Services",
                    List.of(
                            "Move yourself to a safe position away from moving traffic.",
                            "Call 112 immediately if anyone is injured, trapped, unconscious, or in immediate danger.",
                            "Give the exact location, nearby landmark, number of vehicles involved, and number of injured people.",
                            "Do not move a seriously injured person unless there is immediate danger such as fire or moving traffic.",
                            "Warn approaching traffic only if you can do so safely.",
                            "Do not leave the scene if emergency responders need information from you."
                    ),
                    List.of(
                            "Vehicle registration number",
                            "Exact incident location",
                            "Approximate incident time",
                            "Photos or video only when safe",
                            "Details of vehicles involved",
                            "Witness contact details if voluntarily provided"
                    ),
                    List.of(
                            "Submit the SENTINEL report.",
                            "Contact 112 for immediate emergency assistance.",
                            "Cooperate with emergency responders.",
                            "Provide available evidence to the appropriate authority.",
                            "Use the SENTINEL report ID to track the report."
                    )
            );

            case THEFT -> new GuidanceResponse(
                    "HIGH",
                    "112",
                    "Police / Emergency Response Services",
                    List.of(
                            "Move to a safe location before taking further action.",
                            "Confirm that the vehicle was not moved, borrowed, or officially towed.",
                            "Do not chase the vehicle or confront anyone suspected of taking it.",
                            "Call 112 if the theft is in progress or there is an immediate threat.",
                            "Record the last known location and approximate time the vehicle was seen.",
                            "If vehicle tracking is available, share the information with authorities instead of pursuing the vehicle."
                    ),
                    List.of(
                            "Vehicle registration number",
                            "Vehicle make, model, and colour",
                            "Last known location",
                            "Approximate time of theft",
                            "Distinctive vehicle features",
                            "Available CCTV or dashcam footage",
                            "Relevant vehicle documents"
                    ),
                    List.of(
                            "Submit the SENTINEL report.",
                            "Contact 112 when immediate police assistance is required.",
                            "Complete the required police reporting procedure.",
                            "Provide vehicle and ownership information when requested.",
                            "Preserve and provide relevant evidence.",
                            "Track the report using the SENTINEL report ID."
                    )
            );

            case ROBBERY -> new GuidanceResponse(
                    "CRITICAL",
                    "112",
                    "Police / Emergency Response Services",
                    List.of(
                            "Prioritize your personal safety and move away from immediate danger.",
                            "Do not confront, chase, or attempt to physically stop the offender.",
                            "Call 112 as soon as you are in a safe position.",
                            "If weapons are involved, maintain distance and follow emergency operator instructions.",
                            "Observe identifying details only when you can do so safely.",
                            "Seek medical assistance immediately if anyone is injured."
                    ),
                    List.of(
                            "Vehicle registration number if safely observed",
                            "Location and approximate time",
                            "Vehicle type and colour",
                            "Description of events",
                            "Direction of travel if safely observed",
                            "Photos, CCTV, or video if already available",
                            "Witness information if voluntarily provided"
                    ),
                    List.of(
                            "Contact 112 for immediate assistance.",
                            "Submit the SENTINEL report.",
                            "Provide factual information to responding authorities.",
                            "Preserve original evidence without editing it.",
                            "Follow instructions provided by authorities.",
                            "Track the SENTINEL report for status updates."
                    )
            );

            case HARASSMENT -> new GuidanceResponse(
                    "HIGH",
                    "112",
                    "Police / Emergency Response Services",
                    List.of(
                            "Move toward a safe, populated, or secure location.",
                            "Do not confront the person if doing so may increase the danger.",
                            "Call 112 if you are being followed, threatened, blocked, or are in immediate danger.",
                            "Contact a trusted person and share your location if possible.",
                            "Do not follow or chase the suspected vehicle.",
                            "Record vehicle details only when it is safe to do so."
                    ),
                    List.of(
                            "Vehicle registration number if safely visible",
                            "Vehicle type and colour",
                            "Location and time",
                            "Description of the behaviour",
                            "Direction of travel",
                            "Available photos or video obtained safely",
                            "Witness information if available"
                    ),
                    List.of(
                            "Move to safety.",
                            "Contact 112 if there is an immediate threat.",
                            "Submit the SENTINEL report.",
                            "Preserve relevant messages, photos, videos, or other evidence.",
                            "Provide factual information to the appropriate authority.",
                            "Track the submitted report."
                    )
            );

            case OVER_SPEEDING -> new GuidanceResponse(
                    "HIGH",
                    "112",
                    "Traffic Police / Emergency Response Services",
                    List.of(
                            "Do not chase or attempt to stop the speeding vehicle.",
                            "Maintain a safe distance from the vehicle.",
                            "Move away from the vehicle's path if it presents an immediate danger.",
                            "Call 112 if the driving creates an immediate threat to public safety.",
                            "Observe the registration number and direction of travel only if safely possible."
                    ),
                    List.of(
                            "Vehicle registration number",
                            "Vehicle type and colour",
                            "Location",
                            "Approximate time",
                            "Direction of travel",
                            "Description of dangerous driving behaviour",
                            "Dashcam footage if already available"
                    ),
                    List.of(
                            "Submit the SENTINEL report with accurate observations.",
                            "Contact 112 if there is immediate danger.",
                            "Do not pursue the vehicle.",
                            "Preserve any safely obtained evidence.",
                            "Track the report using the SENTINEL report ID."
                    )
            );

            case TRAFFIC_VIOLATION -> new GuidanceResponse(
                    "MEDIUM",
                    "112",
                    "Traffic Police",
                    List.of(
                            "Maintain a safe distance from the vehicle.",
                            "Do not confront the driver.",
                            "Do not attempt to physically stop the vehicle.",
                            "Call 112 only if the violation creates an immediate danger or emergency.",
                            "Record relevant details only when it is safe and lawful to do so."
                    ),
                    List.of(
                            "Vehicle registration number",
                            "Location",
                            "Approximate time",
                            "Type of traffic violation observed",
                            "Direction of travel if relevant",
                            "Photo or video evidence only if safely obtained"
                    ),
                    List.of(
                            "Submit accurate details through SENTINEL.",
                            "Contact emergency services if the situation becomes immediately dangerous.",
                            "Preserve relevant evidence.",
                            "Avoid direct confrontation.",
                            "Track the report for status updates."
                    )
            );

            case HIT_AND_RUN -> new GuidanceResponse(
                    "CRITICAL",
                    "112",
                    "Police / Emergency Response Services",
                    List.of(
                            "Prioritize injured persons and your own safety.",
                            "Call 112 immediately if anyone is injured or in danger.",
                            "Do not chase the fleeing vehicle.",
                            "If safely observed, remember the registration number, colour, type, and direction of travel.",
                            "Note the exact incident location and approximate time.",
                            "Remain available to provide information to emergency responders if it is safe to do so."
                    ),
                    List.of(
                            "Full or partial vehicle registration number",
                            "Vehicle colour and type",
                            "Direction of travel",
                            "Exact incident location",
                            "Approximate incident time",
                            "Photos, video, or dashcam footage",
                            "Witness information if available"
                    ),
                    List.of(
                            "Contact 112 for immediate emergency assistance.",
                            "Submit the SENTINEL report.",
                            "Provide observed vehicle information to authorities.",
                            "Preserve original photos or video.",
                            "Cooperate with responding authorities.",
                            "Track the report using the SENTINEL report ID."
                    )
            );

            case SUSPICIOUS_ACTIVITY -> new GuidanceResponse(
                    "MEDIUM",
                    "112",
                    "Police / Emergency Response Services",
                    List.of(
                            "Maintain a safe distance from the vehicle and its occupants.",
                            "Do not confront anyone or attempt to inspect the vehicle.",
                            "Do not follow or chase the vehicle.",
                            "Observe only from a safe and lawful position.",
                            "Call 112 if you believe there is an immediate threat to a person or public safety.",
                            "Report only what you directly observed and avoid assumptions."
                    ),
                    List.of(
                            "Vehicle registration number if safely visible",
                            "Vehicle type and colour",
                            "Location",
                            "Approximate time observed",
                            "Direction of travel if applicable",
                            "Specific behaviour or activity directly observed"
                    ),
                    List.of(
                            "Submit factual observations through SENTINEL.",
                            "Contact 112 if the situation becomes an immediate emergency.",
                            "Do not personally intervene.",
                            "Preserve relevant information or evidence.",
                            "Track the submitted report."
                    )
            );

            case CRIME_LINKED -> new GuidanceResponse(
                    "CRITICAL",
                    "112",
                    "Police / Emergency Response Services",
                    List.of(
                            "Move away from the vehicle and maintain a safe distance.",
                            "Do not confront, follow, or attempt to detain anyone.",
                            "Call 112 immediately if a crime is occurring or anyone is in immediate danger.",
                            "Do not touch the vehicle or objects that may be connected to the incident.",
                            "Observe identifying information only from a safe position.",
                            "Follow instructions given by emergency responders."
                    ),
                    List.of(
                            "Vehicle registration number if safely visible",
                            "Vehicle description",
                            "Exact location",
                            "Approximate time",
                            "Description of events directly observed",
                            "Direction of travel if applicable",
                            "Available photos, CCTV, or video obtained safely"
                    ),
                    List.of(
                            "Contact 112 when immediate assistance is required.",
                            "Submit the SENTINEL report.",
                            "Do not interfere with or disturb potential evidence.",
                            "Provide factual observations to authorities.",
                            "Preserve original evidence.",
                            "Track the report using the SENTINEL report ID."
                    )
            );

            case VEHICLE_ABANDONED -> new GuidanceResponse(
                    "MEDIUM",
                    "112",
                    "Police / Local Authority",
                    List.of(
                            "Maintain a reasonable distance if the vehicle appears damaged, hazardous, or suspicious.",
                            "Do not enter, open, move, or touch the vehicle.",
                            "Do not handle objects found inside or around the vehicle.",
                            "Call 112 if you observe smoke, fire, suspicious objects, immediate danger, or signs of criminal activity.",
                            "Report the vehicle's location and identifying details from a safe position."
                    ),
                    List.of(
                            "Vehicle registration number if visible",
                            "Vehicle type and colour",
                            "Exact location",
                            "Approximate time first observed",
                            "Visible condition of the vehicle",
                            "Photos only when they can be taken safely"
                    ),
                    List.of(
                            "Submit the SENTINEL report.",
                            "Contact 112 if the vehicle presents an immediate safety or security concern.",
                            "Do not interfere with the vehicle.",
                            "Provide available information to the appropriate authority.",
                            "Track the report for status updates."
                    )
            );
        };
    }
}