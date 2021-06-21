<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="deleteAllUserModal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="" method="post">
                <div class="modal-header">						
                    <h4 class="modal-title">Delete User</h4>
                    <button type="button" class="close" data-bs-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">					
                    <p>Are you sure you want to delete all these User?</p>
                    <p class="text-warning"><small>Information and account of these User will be delete from Database.</small></p>
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-danger" name="userFunction" value="DeleteAll">
                </div>
            </form>
        </div>
    </div>
</div>