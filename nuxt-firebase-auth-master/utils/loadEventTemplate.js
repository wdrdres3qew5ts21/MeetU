export function loadEventTemplate(responseEvent) {
    return {
        elasticEventId: responseEvent.elasticEventId,
        numberOfTicket: responseEvent.numberOfTicket,
        eventName: responseEvent.eventName,
        eventDetail: responseEvent.eventDetail,
        eventPictureCover: responseEvent.eventPictureCover,
        eventPictureLists: responseEvent.eventPictureLists,
        eventStartDate: responseEvent.eventStartDate,
        eventEndDate: responseEvent.eventEndDate,
        createEventDate: responseEvent.createEventDate,
        location: responseEvent.location,
        badge: responseEvent.badge,
        organizeId: responseEvent.organize.organizeId,
        organizeName: responseEvent.organize.organizeName,
        eventTags: responseEvent.eventTags,
        marker: {
            title: responseEvent.eventName,
            detail: responseEvent.eventDetail,
            position: {
                lat: responseEvent.location.geopoint.lat,
                lng: responseEvent.location.geopoint.lon
            }
        }
    };
}