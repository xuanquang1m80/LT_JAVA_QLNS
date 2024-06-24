//Bằng Cấp
document.addEventListener("DOMContentLoaded", function() {
    var editButtons = document.querySelectorAll(".editButton");
    editButtons.forEach(function(button) {
        button.addEventListener("click", function() {
            var id = button.getAttribute("data-id");
            var tenBC = button.getAttribute("data-tenBC");
            var moTa = button.getAttribute("data-moTa");

            document.getElementById("editId").value = id;
            document.getElementById("inputTextEdit").value = tenBC;
            document.getElementById("inputDescriptionEdit").value = moTa;
        });
    });
});
// Chức vụ
 document.addEventListener("DOMContentLoaded", function() {
        var editButtons = document.querySelectorAll(".editButton");
        editButtons.forEach(function(button) {
            button.addEventListener("click", function() {
                var id = button.getAttribute("data-id");
                var tenChucVu = button.getAttribute("data-tenChucVu");
                var luong = button.getAttribute("data-luong");
                var moTa = button.getAttribute("data-moTa");

                document.getElementById("editId").value = id;
                document.getElementById("inputTenChucVuEdit").value = tenChucVu;
                document.getElementById("inputLuongEdit").value = luong;
                document.getElementById("inputMoTaEdit").value = moTa;
            });
        });
    });