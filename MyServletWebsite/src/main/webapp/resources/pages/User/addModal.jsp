<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="addUserModal" class="modal fade" style="display: block;" aria-modal="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form action="" method="post">
                <div class="modal-header">						
                    <h4 class="modal-title">Add new User</h4>
                    <button type="button" class="close" data-bs-dismiss="modal" aria-hidden="true">×</button>
                </div>
                <div class="modal-body">					
                    <div class="form-group">
                        <label>Username</label>
                        <input type="text" class="form-control" required="" name="addUsername">
                    </div>
                    <div class="form-group">
                        <label>Birthday</label>
                        <input type="date" class="form-control" required="" name="addBirthday">
                    </div>
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" required="" name="addEmail">
                    </div>
                    <div class="form-group">
                        <label>Company</label>
                        <input type="text" class="form-control" required="" name="addCompany">
                    </div>
                    <div class="form-group">
                        <label>Home Town</label>
                        <input type="text" class="form-control" required="" name="addHomeTown">
                    </div>					
                </div>
                <div class="modal-footer">
                    <input type="button" class="btn btn-default" data-bs-dismiss="modal" value="Cancel">
                    <input type="submit" class="btn btn-success" name="userFunction" value="Add">
                </div>
            </form>
        </div>
    </div>
</div>
