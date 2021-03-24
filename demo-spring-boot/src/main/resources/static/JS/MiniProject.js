function startTime() {
  var today = new Date();
  var hour = today.getHours(); hour = checkTime(hour);
  var minute = today.getMinutes(); minute = checkTime(minute);
  var second = today.getSeconds(); second = checkTime(second);
  var day = today.getDate(); day = checkTime(day);
  var month = today.getMonth()+1; month = checkTime(month);
  var year = today.getFullYear();
  document.getElementById("datetime").innerHTML = hour+":"+minute+":"+second+" "+day+"/"+month+"/"+year;
  var t = setTimeout(startTime, 500);
}
function checkTime(i) {
  if (i < 10) {i = "0" + i};
  return i;
}

function Person(id, name, joinDate, role, status) {
    this.id = id;
    this.name = name;
    this.joinDate = joinDate;
    this.role = role;
    this.status = status;
}

let listPerson = [
    new Person(1, "Michel Holz", "2020-07-15", "Leader", "Active"),
    new Person(2, "Paula Wilson", "2018-01-01", "Developer", "Inactive"),
    new Person(3, "Antonio Moreno", "2019-05-22", "Tester", "Active"),
    new Person(4, "Mary Saveley", "2019-04-12", "Comtor", "Active"),
    new Person(5, "Martin Sommer", "2020-03-21", "Developer", "Inactive"),
    new Person(6, "Michel Holz", "2020-07-15", "Leader", "Active"),
    new Person(7, "Paula Wilson", "2018-01-01", "Developer", "Inactive"),
    new Person(8, "Antonio Moreno", "2019-05-22", "Tester", "Active"),
    new Person(9, "Mary Saveley", "2019-04-12", "Comtor", "Active"),
    new Person(10, "Martin Sommer", "2020-03-21", "Developer", "Inactive"),
    new Person(11, "Michel Holz", "2020-07-15", "Leader", "Active"),
    new Person(12, "Paula Wilson", "2018-01-01", "Developer", "Inactive")
]

// jQuery Set, load data to html page
for (let i = 0; i < listPerson.length; i++) {
    $("#row"+i).after("<tr id=\"row"+(i+1)+"\"><td id=\"no"+(i+1)+"\">"+listPerson[i].id+"</td><td id=\"name"+(i+1)+"\">"+listPerson[i].name+"</td><td id=\"joinDate"+(i+1)+"\">"+listPerson[i].joinDate+"</td><td id=\"role"+(i+1)+"\">"+listPerson[i].role+"</td><td id=\"status"+(i+1)+"\">"+listPerson[i].status+"</td><td id=\"actionEdit"+(i+1)+"\"><div class=\"btn btn-info function\" id=\"editBtn"+(i+1)+"\" data-toggle=\"modal\" onclick=\"showEditModalAndGetIndex(this.id)\"><p>Edit</p></div></td><td id=\"actionDelete"+(i+1)+"\"><div class=\"btn btn-danger function\" id=\"deleteBtn"+(i+1)+"\" data-toggle=\"modal\" onclick=\"showDeleteModalAndGetIndex(this.id)\"><p>Delete</p></div></td></tr>");
    // if(listPerson[i].status == "Active")
    //     $("#status"+(i+1)).html("<img src=\"active.png\" class=\"statusImg\">")
    // else 
    //     $("#status"+(i+1)).html("<img src=\"inactive.png\" class=\"statusImg\">")
}


function addUser() {
    let id = listPerson.length+1;
    let name = $("#addNameForm").val();
    let joinDate = $("#addJoinDateForm").val();
    let role = $("#addRoleForm").val();
    let status = $("#addStatusForm").val(); 

    if(name=="") {
        alert("Name must be fill")
    }
    else if(joinDate=="") {
        alert("Join date must be fill")
    }
    else if(role=="default") {
        alert("Role must be fill")
    }
    else {
        listPerson.push(new Person(id, name, joinDate, role, status));
        $("#row"+(listPerson.length-1)).after("<tr id=\"row"+listPerson.length+"\"><td id=\"no"+listPerson.length+"\">"+listPerson[listPerson.length-1].id+"</td><td id=\"name"+listPerson.length+"\">"+listPerson[listPerson.length-1].name+"</td><td id=\"joinDate"+listPerson.length+"\">"+listPerson[listPerson.length-1].joinDate+"</td><td id=\"role"+listPerson.length+"\">"+listPerson[listPerson.length-1].role+"</td><td id=\"status"+listPerson.length+"\">"+listPerson[listPerson.length-1].status+"</td><td id=\"actionEdit"+listPerson.length+"\"><div class=\"btn btn-info function\" id=\"editBtn"+listPerson.length+"\" data-toggle=\"modal\" onclick=\"showEditModalAndGetIndex(this.id)\"><p>Edit</p></div></td><td id=\"actionDelete"+listPerson.length+"\"><div class=\"btn btn-danger function\" id=\"deleteBtn"+listPerson.length+"\" data-toggle=\"modal\" onclick=\"showDeleteModalAndGetIndex(this.id)\"><p>Delete</p></div></td></tr>");
        // if(listPerson[listPerson.length-1].status == "Active")
        //     $("#status"+(listPerson.length)).html("<img src=\"active.png\" class=\"statusImg\">")
        // else 
        //     $("#status"+(listPerson.length)).html("<img src=\"inactive.png\" class=\"statusImg\">")
        alert("Add user successful");
        $("#addModal").modal("hide");
    }
}


function showDeleteModalAndGetIndex(id) {
    let buttonIndex = id.replace(id.slice(0,9),"");
    // console.log(buttonIndex);
    let name = $("#name"+buttonIndex).text();
    $("#deleteModal").modal("show");
    $("#deleteMessage").html("Do yo want to delete "+name+" ?")
    $("#deleteConfirm").attr("dataIndex",buttonIndex);
}
function deleteUser(event){
    let index = event.target.getAttribute("dataIndex");
    $("#row"+index).remove()
    alert("Delete user successful");
    $("#deleteModal").modal("hide");
}


function showEditModalAndGetIndex(id) {
    let buttonIndex = id.replace(id.slice(0,7),"");
    $("#editNameForm").val($("#name"+buttonIndex).text());
    $("#editJoinDateForm").val($("#joinDate"+buttonIndex).text());
    $("#editRoleForm").val($("#role"+buttonIndex).text());
    $("#editStatusForm").val($("#status"+buttonIndex).text()); 
    $("#editModal").modal("show");
    $("#editConfirm").attr("dataIndex",buttonIndex);
}
function editUser(event) {
    let id = event.target.getAttribute("dataIndex");
    console.log(id);
    let name = $("#editNameForm").val();
    let joinDate = $("#editJoinDateForm").val();
    let role = $("#editRoleForm").val();
    let status = $("#editStatusForm").val(); 

    if(name=="") {
        alert("Name must be fill")
    }
    else if(joinDate=="") {
        alert("Join date must be fill")
    }
    else if(role=="default") {
        alert("Role must be fill")
    }
    else {
        $("#name"+id).text(name);
        $("#joinDate"+id).text(joinDate);
        $("#role"+id).text(role);
        $("#status"+id).text(status);
        // if(status == "Active")
        //     $("#status"+id).html("<img src=\"active.png\" class=\"statusImg\">")
        // else 
        //     $("#status"+id).html("<img src=\"inactive.png\" class=\"statusImg\">")
        alert("Edit user successful");
        $("#editModal").modal("hide");
    }
}


//pagination
let currentPage = 1;
const maxRow = 5;
for (let i = 0; i < array.length; i++) {
    
}
