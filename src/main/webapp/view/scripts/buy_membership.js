function getPrice(clientDiscount) {
    var membershipDurationForm = document.getElementById("membershipDuration");
    var selectedValue = membershipDurationForm.options[membershipDurationForm.selectedIndex].value;
    var price = "";
    if (clientDiscount == null) {
        clientDiscount = 0;
    }
    var multiplier = (100.00 - clientDiscount) / 100;
    switch (selectedValue) {
        case "1":
            price += "Price: " + (30.00 * multiplier) + " BYN";
            break;
        case "3":
            price += "Price: " + (85.00 * multiplier) + " BYN";
            break;
        case "6":
            price += "Price: " + (160.00 * multiplier) + " BYN";
            break;
        case "12":
            price += "Price: " + (300.00 * multiplier) + " BYN";
            break;
        default:
            price += "Choose again, please";
    }
    document.getElementById("membershipPrice").innerHTML = price;
}
