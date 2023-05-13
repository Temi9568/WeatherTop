// function validateForm(event) {
//     event.preventDefault(); // Stops default form submit behaviour
//
//     // AddStation Form - Validation
//     if (event.target.id === "addStation") {
//         var name = document.getElementById("addStationName").value;
//         var lat = parseFloat(document.getElementById("latInput").value);
//         var lng = parseFloat(document.getElementById("lngInput").value);
//         if (isNaN(lat) || isNaN(lng)) {
//             alert("Please enter valid latitude and longitude values.");
//             return false;
//         } else if (name.length <= 0) {
//             alert("The station name you entered is invalid.")
//             return false;
//         }
//     }
//     // TODO - REALISTICALLY ADD SOME REGEX FOR THESE TBH, MANUAL VALIDATION IS POINTLESS
//     event.target.submit();
// }