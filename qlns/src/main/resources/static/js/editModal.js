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
//Chuc Vu
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

            var ngayTao = new Date().toISOString().slice(0, 19).replace('T', ' ');
            document.getElementById("inputNgayTaoEdit").value = ngayTao;
        });
    });
});



//Chuyên Môn
 document.addEventListener("DOMContentLoaded", function() {
        var editButtons = document.querySelectorAll(".editButton");
        editButtons.forEach(function(button) {
            button.addEventListener("click", function() {
                var id = button.getAttribute("data-id");
                var tenCM = button.getAttribute("data-tenCM");
                var moTa = button.getAttribute("data-moTa");

                document.getElementById("editId").value = id;
                document.getElementById("inputTextEdit").value = tenCM;
                document.getElementById("inputDescriptionEdit").value = moTa;
            });
        });
    });
 $(document).ready(function() {
     $('#exportExcelBtn').click(function() {
         // Lấy dữ liệu từ bảng HTML
         var table = document.getElementById("example");
         var data = XLSX.utils.table_to_book(table);

         // Tạo file Excel và tải xuống
         XLSX.writeFile(data, "DanhSachBangTinhLuong.xlsx");
     });
 });

document.addEventListener('DOMContentLoaded', function() {
    const detailModal = document.getElementById('ChiTietTinhLuong');

    detailModal.addEventListener('show.bs.modal', function(event) {
        const button = event.relatedTarget;
        const luongId = button.getAttribute('data-id');

        fetch(`/tinhluong/details/${luongId}`)
            .then(response => response.json())
            .then(data => {
                document.getElementById('chiTietTenNV').innerText = data.nhanVien.tenNV;
                document.getElementById('chiTietLuongThang').innerText = data.luongThang;
                document.getElementById('chiTietNgayCong').innerText = data.ngayCong;
                document.getElementById('chiTietNgayNghi').innerText = data.ngayNghi;
                document.getElementById('chiTietPhuCap').innerText = data.phuCap;
                document.getElementById('chiTietTamUng').innerText = data.tamUng;
                document.getElementById('chiTietThucLanh').innerText = data.thucLanh;
            })
            .catch(error => console.error('Error:', error));
    });
});


