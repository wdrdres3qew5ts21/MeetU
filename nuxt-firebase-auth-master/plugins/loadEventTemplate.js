export function loadEventTemplate(responseEvent) {
    axios
      .get(`${process.env.EVENT_SERVICE}/event/${elasticEventId}`)
      .then(response => {
        console.log("------------ Async Data  -----------");
        let data = response.data;
        return {
          elasticEventId: data.elasticEventId,
          numberOfTicket: data.numberOfTicket,
          eventName: data.eventName,
          eventDetail: data.eventDetail,
          eventPictureCover: data.eventPictureCover,
          eventPictureLists: data.eventPictureLists,
          eventStartDate: data.eventStartDate,
          eventEndDate: data.eventEndDate,
          createEventDate: data.createEventDate,
          location: data.location,
          badge: data.badge,
          organizeId: data.organize.organizeId,
          organizeName: data.organize.organizeName,
          eventTags: data.eventTags,
          marker: {
            title: data.eventName,
            detail: data.eventDetail,
            position: {
              lat: data.location.geopoint.lat,
              lng: data.location.geopoint.lon
            }
          }
        };
      })
      .catch(err => {
        console.log("!!!!!!!!!!!!!!!!! Boom Not found !!!!!!!!!!");
        console.log(err);
      });
}