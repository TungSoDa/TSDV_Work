<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
    <meta charset="UTF-8">
    <title>User Information</title>

    <link rel="icon" type="image/png" href="./resources/assets/images/user/favicon.png"/>
    <!-- Vendor CSS Files -->
    <link href="./resources/assets/vendor/bootstrap-5/css/bootstrap.min.css" rel="stylesheet">
    <link href="./resources/assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="./resources/assets/css/userInfo/style.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="col-md-12">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="col-md-8 col-sm-6">
                        <h3 class="panel-title">User Information</h3>
                    </div>
                    <div class="col-md-2">
                        <a data-bs-target="#deleteAllUserModal" class="btn btn-danger" id="function-button" data-bs-toggle="modal">
                            <svg aria-hidden="true" width="20" height="20" focusable="false" data-prefix="fal" data-icon="trash" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" class="svg-inline--fa fa-trash fa-w-14 fa-2x">
                                <path fill="currentColor" d="M440 64H336l-33.6-44.8A48 48 0 0 0 264 0h-80a48 48 0 0 0-38.4 19.2L112 64H8a8 8 0 0 0-8 8v16a8 8 0 0 0 8 8h18.9l33.2 372.3a48 48 0 0 0 47.8 43.7h232.2a48 48 0 0 0 47.8-43.7L421.1 96H440a8 8 0 0 0 8-8V72a8 8 0 0 0-8-8zM171.2 38.4A16.1 16.1 0 0 1 184 32h80a16.1 16.1 0 0 1 12.8 6.4L296 64H152zm184.8 427a15.91 15.91 0 0 1-15.9 14.6H107.9A15.91 15.91 0 0 1 92 465.4L59 96h330z" class=""></path>
                            </svg>
                            <span>Delete All User</span>
                        </a>
                    </div>
                    <div class="col-md-2">
                        <a data-bs-target="#addUserModal" class="btn btn-success" id="function-button" data-bs-toggle="modal">
                            <svg aria-hidden="true" width="20" height="20" focusable="false" data-prefix="far" data-icon="user-plus" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512" class="svg-inline--fa fa-user-plus fa-w-20 fa-2x">
                                <path fill="currentColor" d="M224 288c79.5 0 144-64.5 144-144S303.5 0 224 0 80 64.5 80 144s64.5 144 144 144zm0-240c52.9 0 96 43.1 96 96s-43.1 96-96 96-96-43.1-96-96 43.1-96 96-96zm89.6 256c-28.7 0-42.5 16-89.6 16-47.1 0-60.8-16-89.6-16C60.2 304 0 364.2 0 438.4V464c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48v-25.6c0-74.2-60.2-134.4-134.4-134.4zM400 464H48v-25.6c0-47.6 38.8-86.4 86.4-86.4 14.6 0 38.3 16 89.6 16 51.7 0 74.9-16 89.6-16 47.6 0 86.4 38.8 86.4 86.4V464zm224-248h-72v-72c0-8.8-7.2-16-16-16h-16c-8.8 0-16 7.2-16 16v72h-72c-8.8 0-16 7.2-16 16v16c0 8.8 7.2 16 16 16h72v72c0 8.8 7.2 16 16 16h16c8.8 0 16-7.2 16-16v-72h72c8.8 0 16-7.2 16-16v-16c0-8.8-7.2-16-16-16z" class=""></path>
                            </svg>
                            <span>Add New User</span>
                        </a>
                    </div>
                </div>
                <div class="panel-body">
                    <input type="text" class="form-control" id="dev-table-filter" data-action="filter" data-filters="#dev-table" placeholder="Search user" />
                </div>
                <table class="table table-hover" id="dev-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Birthday</th>
                            <th>Email</th>
                            <th>Company</th>
                            <th>Home Town</th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="users" items="${userList}">
                            <tr>
                                <td class="userID">${users.id}</td>
                                <td class="userName">${users.username}</td>
                                <td class="userBirthday">${users.birthday}</td>
                                <td class="userEmail">${users.email}</td>
                                <td class="userCompany">${users.company}</td>
                                <td class="userHomeTown">${users.homeTown}</td>
                                <td>
                                    <a data-bs-target="#editUserModal" class="edit" id="function-button" data-bs-toggle="modal">
                                        <svg aria-hidden="true" width="18" height="18" data-bs-toggle="tooltip" title="Edit" data-original-title="Edit" focusable="false" data-prefix="far" data-icon="user-edit" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512" class="svg-inline--fa fa-user-edit fa-w-20 fa-2x text-info">
                                            <path fill="currentColor" d="M358.9 433.3l-6.8 61c-1.1 10.2 7.5 18.8 17.6 17.6l60.9-6.8 137.9-137.9-71.7-71.7-137.9 137.8zM633 268.9L595.1 231c-9.3-9.3-24.5-9.3-33.8 0l-41.8 41.8 71.8 71.7 41.8-41.8c9.2-9.3 9.2-24.4-.1-33.8zM223.9 288c79.6.1 144.2-64.5 144.1-144.1C367.9 65.6 302.4.1 224.1 0 144.5-.1 79.9 64.5 80 144.1c.1 78.3 65.6 143.8 143.9 143.9zm-4.4-239.9c56.5-2.6 103 43.9 100.4 100.4-2.3 49.2-42.1 89.1-91.4 91.4-56.5 2.6-103-43.9-100.4-100.4 2.3-49.3 42.2-89.1 91.4-91.4zM134.4 352c14.6 0 38.3 16 89.6 16 51.7 0 74.9-16 89.6-16 16.7 0 32.2 5 45.5 13.3l34.4-34.4c-22.4-16.7-49.8-26.9-79.9-26.9-28.7 0-42.5 16-89.6 16-47.1 0-60.8-16-89.6-16C60.2 304 0 364.2 0 438.4V464c0 26.5 21.5 48 48 48h258.3c-3.8-14.6-2.2-20.3.9-48H48v-25.6c0-47.6 38.8-86.4 86.4-86.4z" class=""></path>
                                        </svg>
                                    </a>                                
                                </td>
                                <td>
                                    <a data-bs-target="#deleteUserModal" class="delete" id="function-button" data-bs-toggle="modal">
                                        <svg aria-hidden="true" width="18" height="18" data-bs-toggle="tooltip" title="Delete" data-original-title="Delete" focusable="false" data-prefix="far" data-icon="user-minus" role="img" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512" class="svg-inline--fa fa-user-minus fa-w-20 fa-2x text-danger">
                                            <path fill="currentColor" d="M624 216H432c-8.8 0-16 7.2-16 16v16c0 8.8 7.2 16 16 16h192c8.8 0 16-7.2 16-16v-16c0-8.8-7.2-16-16-16zm-310.4 88c-28.8 0-42.4 16-89.6 16-47.1 0-60.8-16-89.6-16C60.2 304 0 364.2 0 438.4V464c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48v-25.6c0-74.2-60.2-134.4-134.4-134.4zM400 464H48v-25.6c0-47.6 38.8-86.4 86.4-86.4 14.6 0 38.3 16 89.6 16 51.7 0 74.9-16 89.6-16 47.6 0 86.4 38.8 86.4 86.4V464zM224 288c79.5 0 144-64.5 144-144S303.5 0 224 0 80 64.5 80 144s64.5 144 144 144zm0-240c52.9 0 96 43.1 96 96s-43.1 96-96 96-96-43.1-96-96 43.1-96 96-96z" class=""></path>
                                        </svg>
                                    </a>                                
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <jsp:include page="addModal.jsp"/>

    <jsp:include page="editModal.jsp"/>
    
    <jsp:include page="deleteModal.jsp"/>

    <jsp:include page="deleteAllModal.jsp"/>

    <!-- Vendor JS Files -->
    <script src="./resources/assets/vendor/bootstrap-5/js/bootstrap.min.js"></script>
    <script src="./resources/assets/vendor/jquery/jquery-3.2.1.min.js"></script>

    <!-- Template Main JS File -->
    <script>
        // change container z-index from 2000 to 1 when click button
        $(document).on('click', '#function-button', function() { 
            $(".container").css("z-index", 1);
        });
    </script>
    <script src="./resources/assets/js/userInfo/main.js"></script>
</body>
</html>