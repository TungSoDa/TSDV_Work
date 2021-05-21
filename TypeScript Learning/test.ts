// boolean, number, string, array, enum, tuple, any, void
// var isDone: boolean = true;
var myName: string = "Dang Son Tung";
var height: number = 9;

var list: any[] = [];
var check: Array<any> = [];

enum Color{Red, Green, Blue};
var c: Color = Color.Green
var colorName = Color[0] // kết quả sẽ là Red


//function
// trả về kiểu number với tham số đầu vào là 2 number 
function add(x: number, y: number): number {
	return x+y;
}
var myAdd = function(x: number, y: number): number { return x+y; };

// hàm có tham số đầu vào
function buildName1(firstName: string, lastName = "Dang") {
	return lastName + " " + firstName;
}

//viêc nhập lastName có thể không cần nữa
function buildName2(firstName: string, lastName?: string) {
	if (lastName)
			return lastName  + " " + firstName;
	else
			return firstName;
}
// firstName sẽ là bắt buộc phải nhập. còn các thành phần còn lại sẽ được gộp chung vào một biến array
function buildName3(firstName: string, ...restOfName: string[]) {
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
class Tuong {
	ten : string;
	mota : string;
	kinang : string[];

	constructor(ten : string, mota : string, kinang : string[]) {
			this.ten = ten;
			this.mota = mota;
			this.kinang = kinang;
	}

	ShowInfo () {
			let kn = '';
			for (var i = 0; i < this.kinang.length; i++) {
					kn += this.kinang[i] + " | ";
			}
			return `
					Tên Tướng : ${this.ten}
					Mô Tả : ${this.mota}
					Ki Năng : ${kn}
			`;
	}
}
let ashe = new Tuong('Ashe', 'Cung Băng', ['Chú tâm tiễn', 'Tán Xạ tiễn', 'Ưng tiễn', 'Đại băng tiễn']);
console.log(ashe.ShowInfo());

// Class SatThu ke thua tu class tuong
class SatThu extends Tuong {
	donsatthu : string;

	constructor(ten : string, mota : string, kinang : string[], donsatthu : string) {
			super(ten, mota,  kinang);
			this.donsatthu = donsatthu;
	}

	ShowInfo () {
			let kn = '';
			for (var i = 0; i < this.kinang.length; i++) {
					kn += this.kinang[i] + " | ";
			}
			return `
					Tên Tướng : ${this.ten}
					Mô Tả : ${this.mota}
					Kĩ Năng : ${kn}
					Đòn Sát Thủ : ${this.donsatthu}
			`;
	}
}
let talon = new SatThu('Talon', 'Cung Băng', ['Chú tâm tiễn', 'Tán Xạ tiễn', 'Ưng tiễn', 'Đại băng tiễn'], 'Sát Thủ Bóng Đêm');
console.log(talon.ShowInfo());

// module
module Adroid {
	export class String {
		test () : void {
			console.log('String');
		}
	}
	export class Number {
		test () : void {
			console.log('number');
		}
	}
}
var coca = new Adroid.String();
var pessi = new Adroid.Number();
pessi.test();
coca.test();