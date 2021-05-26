var studentList = [
    { 
        id: 17020965, 
        name: "Đặng Sơn Tùng", 
        point: 90 
    },
    { 
        id: 17021119, 
        name: "Nguyễn Xuân Tự", 
        point: 80 
    },
    { 
        id: 17020965, 
        name: "Dương Tuấn Phương", 
        point: 70 
    }
];

function fillData() {
    for (let i = 0; i < studentList.length; i++) {
        let row =
            `<tr>
                <th scope="row">` + $(studentList[i].id) + `</th>
                <td>` + $(studentList[i].name) + `</td>
                <td>` + $(studentList[i].point) + `</td>
                <td><a href="">Xem chi tiết</a></td>
            </tr>`
        $('#studentList').append(row);
    }
}