var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({ __proto__: [] } instanceof Array && function (d, b) { d.__proto__ = b; }) ||
            function (d, b) { for (var p in b) if (Object.prototype.hasOwnProperty.call(b, p)) d[p] = b[p]; };
        return extendStatics(d, b);
    };
    return function (d, b) {
        if (typeof b !== "function" && b !== null)
            throw new TypeError("Class extends value " + String(b) + " is not a constructor or null");
        extendStatics(d, b);
        function __() { this.constructor = d; }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
// boolean, number, string, array, enum, tuple, any, void
// var isDone: boolean = true;
var myName = "Dang Son Tung";
var height = 9;
var list = [];
var check = [];
var Color;
(function (Color) {
    Color[Color["Red"] = 0] = "Red";
    Color[Color["Green"] = 1] = "Green";
    Color[Color["Blue"] = 2] = "Blue";
})(Color || (Color = {}));
;
var c = Color.Green;
var colorName = Color[0]; // kết quả sẽ là Red
//function
// trả về kiểu number với tham số đầu vào là 2 number 
function add(x, y) {
    return x + y;
}
var myAdd = function (x, y) { return x + y; };
// hàm có tham số đầu vào
function buildName1(firstName, lastName) {
    if (lastName === void 0) { lastName = "Dang"; }
    return lastName + " " + firstName;
}
//viêc nhập lastName có thể không cần nữa
function buildName2(firstName, lastName) {
    if (lastName)
        return lastName + " " + firstName;
    else
        return firstName;
}
// firstName sẽ là bắt buộc phải nhập. còn các thành phần còn lại sẽ được gộp chung vào một biến array
function buildName3(firstName) {
    var restOfName = [];
    for (var _i = 1; _i < arguments.length; _i++) {
        restOfName[_i - 1] = arguments[_i];
    }
    return restOfName.join(" ") + " " + firstName;
}
var result1 = buildName1("Tung");
console.log(result1);
var result2 = buildName1("Tung", "Nguyen");
console.log(result2);
var result3 = buildName2("Tung");
console.log(result3);
var result4 = buildName2("Tung", "Nguyen");
console.log(result4);
var result5 = buildName3("Tung", "Dang", "Nguyen", "Son");
console.log(result5);
// class
var Tuong = /** @class */ (function () {
    function Tuong(ten, mota, kinang) {
        this.ten = ten;
        this.mota = mota;
        this.kinang = kinang;
    }
    Tuong.prototype.ShowInfo = function () {
        var kn = '';
        for (var i = 0; i < this.kinang.length; i++) {
            kn += this.kinang[i] + " | ";
        }
        return "\n\t\t\t\t\tT\u00EAn T\u01B0\u1EDBng : " + this.ten + "\n\t\t\t\t\tM\u00F4 T\u1EA3 : " + this.mota + "\n\t\t\t\t\tKi N\u0103ng : " + kn + "\n\t\t\t";
    };
    return Tuong;
}());
var ashe = new Tuong('Ashe', 'Cung Băng', ['Chú tâm tiễn', 'Tán Xạ tiễn', 'Ưng tiễn', 'Đại băng tiễn']);
console.log(ashe.ShowInfo());
// Class SatThu ke thua tu class tuong
var SatThu = /** @class */ (function (_super) {
    __extends(SatThu, _super);
    function SatThu(ten, mota, kinang, donsatthu) {
        var _this = _super.call(this, ten, mota, kinang) || this;
        _this.donsatthu = donsatthu;
        return _this;
    }
    SatThu.prototype.ShowInfo = function () {
        var kn = '';
        for (var i = 0; i < this.kinang.length; i++) {
            kn += this.kinang[i] + " | ";
        }
        return "\n\t\t\t\t\tT\u00EAn T\u01B0\u1EDBng : " + this.ten + "\n\t\t\t\t\tM\u00F4 T\u1EA3 : " + this.mota + "\n\t\t\t\t\tK\u0129 N\u0103ng : " + kn + "\n\t\t\t\t\t\u0110\u00F2n S\u00E1t Th\u1EE7 : " + this.donsatthu + "\n\t\t\t";
    };
    return SatThu;
}(Tuong));
var talon = new SatThu('Talon', 'Cung Băng', ['Chú tâm tiễn', 'Tán Xạ tiễn', 'Ưng tiễn', 'Đại băng tiễn'], 'Sát Thủ Bóng Đêm');
console.log(talon.ShowInfo());
// namespace
var Adroid;
(function (Adroid) {
    var String = /** @class */ (function () {
        function String() {
        }
        String.prototype.test = function () {
            console.log('String');
        };
        return String;
    }());
    Adroid.String = String;
    var Number = /** @class */ (function () {
        function Number() {
        }
        Number.prototype.test = function () {
            console.log('number');
        };
        return Number;
    }());
    Adroid.Number = Number;
})(Adroid || (Adroid = {}));
var coca = new Adroid.String();
var pessi = new Adroid.Number();
pessi.test();
coca.test();
