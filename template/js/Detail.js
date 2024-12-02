$(document).ready(function () {
    // Xử lý khi nhấn nút Accept Request
    $("#accept-request-btn").on("click", function (e) {
        e.preventDefault(); // Ngăn hành động mặc định của thẻ <a>

        // Kiểm tra trạng thái hiện tại
        const isAccepted = $(this).hasClass("accepted");

        if (!isAccepted) {
            // Nếu chưa được Accept, hiển thị khung chat và đổi sang Cancel
            $("#chat-section").show(); // Hiển thị khung chat
            $("#open-modal-btn").show();
            $(this).text("Cancel Request"); // Thay đổi nội dung nút
            $(this).removeClass("btn-primary").addClass("btn-danger"); // Đổi màu nút
            $(this).addClass("accepted"); // Đánh dấu trạng thái
        } else {
            // Nếu đã Accept, ẩn khung chat và đổi lại thành Accept
            $("#chat-section").hide(); // Ẩn khung chat
            $("#open-modal-btn").hide();
            $(this).text("Accept Request"); // Đổi lại nội dung nút
            $(this).removeClass("btn-danger").addClass("btn-primary"); // Đổi lại màu nút
            $(this).removeClass("accepted"); // Xóa trạng thái
        }
    });

    $("#open-modal-btn").on("click", function (e) {
        e.preventDefault();
        $("#item-modal").fadeIn(); // Hiển thị modal
    });

    // Đóng modal
    $("#close-modal, #cancel-btn").on("click", function () {
        $("#item-modal").fadeOut(); // Ẩn modal
    });

    // Quản lý số lượng
    const maxQuantity = 10; // Số lượng tối đa
    let currentQuantity = 0;

    $("#increase-qty").on("click", function () {
        if (currentQuantity < maxQuantity) {
            currentQuantity++;
            $("#item-quantity").text(currentQuantity);
        }
    });

    $("#decrease-qty").on("click", function () {
        if (currentQuantity > 0) {
            currentQuantity--;
            $("#item-quantity").text(currentQuantity);
        }
    });

    // Xác nhận
    $("#confirm-btn").on("click", function () {
        const itemName = $("#item-name").text();
        alert(`Confirmed ${itemName} with quantity: ${currentQuantity}`);
        $("#item-modal").fadeOut(); // Ẩn modal sau khi xác nhận
    });
});
