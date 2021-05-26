const BENCHMARK = 30;

function clickHocLucYeu(id) {
    if (document.getElementById(id).checked) {
        $('#' + id.replace("input", "point")).html("-3");
        return -3;
    } else {
        $('#' + id.replace("input", "point")).html("0");
        return 0;
    }
}

function clickCanhBaoHocVu(id) {
    if (document.getElementById(id).checked) {
        $('#' + id.replace("input", "point")).html("-5");
        return -5;
    } else {
        document.getElementById(id.replace("input", "point")).innerHTML = "0";
        return 0;
    }
}

function clickDangKiKhongDuTin(id) {
    if (document.getElementById(id).checked) {
        $('#' + id.replace("input", "point")).html("-5");
        return -5;
    } else {
        $('#' + id.replace("input", "point")).html("0");
        return 0;
    }
}

function fillCamThiBoThi(id) {
    if (document.getElementById(id).onchange) {
        let number = document.getElementById(id).value * (-2);
        $('#' + id.replace("input", "point")).html(number);
        return number;
    }
}

// function fillKyLuatThi(id) {
//     if (document.getElementById(id).onchange) {
//         if ($("#" + id).val() == "Khiển trách") {
//             number = -BENCHMARK / 4;
//             $('#' + id.replace("input", "point")).html(number);
//             return number;
//         }
//         if ($("#" + id).val() == "Cảnh cáo") {
//             number = -BENCHMARK / 2;
//             $('#' + id.replace("input", "point")).html(number);
//             return number;
//         }
//         if ($("#" + id).val() == "Đình chỉ") {
//             number = -BENCHMARK;
//             $('#' + id.replace("input", "point")).html(number);
//             return number;
//         }
//         if ($("#" + id).val() == "Không") {
//             number = 0;
//             $('#' + id.replace("input", "point")).html(number);
//             return number;
//         }
//     }
// }

function totalPoint1(id) {
    let total = BENCHMARK;

    console.log("here")

    let oldPointHocLucYeu = parseInt($('#pointHocLucYeu').text());
    if (document.getElementById("inputHocLucYeu").checked) {
        total = total + oldPointHocLucYeu;
    } else {
        total = total - oldPointHocLucYeu;
    }

    let oldPointCanhBaoHocVu = parseInt($('#pointCanhBaoHocVu').text());
    if (document.getElementById("inputCanhBaoHocVu").checked) {
        total = total + oldPointCanhBaoHocVu;
    } else {
        total = total - oldPointCanhBaoHocVu;
    }

    let oldPointDangKiKhongDuTin = parseInt($('#pointDangKiKhongDuTin').text());
    if (document.getElementById("inputDangKiKhongDuTin").checked) {
        total = total + oldPointDangKiKhongDuTin;
    } else {
        total = total - oldPointDangKiKhongDuTin;
    }

    let oldPointCamThiBoThi = parseInt($('#pointCamThiBoThi').text());
    if (document.getElementById("inputCamThiBoThi").onchange) {
        total = total + oldPointCamThiBoThi;
    } else {
        total = total - oldPointCamThiBoThi;
    }
    $('#' + id).html(total);
} 