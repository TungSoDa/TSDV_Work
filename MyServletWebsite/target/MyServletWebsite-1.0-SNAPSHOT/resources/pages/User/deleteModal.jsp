<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="deleteUserModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="" method="post">
                <div class="modal-header">						
                    <h4 class="modal-title">Delete User</h4>
                    <button type="button" class="close" data-bs-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">					
                    <p>Are you sure you want to delete User <input id="deleteUserID" name="deleteUserID" style="display: none;"> with username is <span id="deleteUsername"></span>?</p>
                    <p class="text-warning"><small>Information and account of this User will be delete from Database.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-danger" name="userFunction" value="Delete">
                </div>
            </form>
        </div>
    </div>
</div>
