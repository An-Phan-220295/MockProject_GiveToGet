$(document).ready(function () {
    // Add new item row
    $("#add-item-btn").on("click", function () {
        const newItemRow = `
            <div class="mb-3 row gx-2 align-items-center">
                <div class="col-md-3">
                    <input
                        type="text"
                        class="form-control item-name"
                        placeholder="Name"
                    />
                </div>
                <div class="col-md-2">
                    <input
                        type="number"
                        class="form-control item-quantity"
                        min="1"
                        placeholder="Quantity"
                    />
                </div>
                <div class="col-md-3 ms-auto text-end">
                    <button type="button" class="btn btn-danger btn-sm remove-item-btn">
                        Remove
                    </button>
                </div>
            </div>`;
        $("#items-container").append(newItemRow);
    });

    // Remove an item row
    $(document).on("click", ".remove-item-btn", function () {
        $(this).closest(".row").remove();
    });

    // Form validation
    $("#create-request-form").on("submit", function (e) {
        e.preventDefault(); // Prevent default form submission

        let isValid = true;
        let errorMessage = "Please fill all mandatory fields";

        // Validate Title, Description, and Image Upload
        const title = $("#title");
        const description = $("#description");
        const imageUpload = $("#image-upload");

        validateField(title);
        validateField(description);

        if (!imageUpload.val()) {
            isValid = false;
            setFieldError(imageUpload);
        } else {
            setFieldValid(imageUpload);
        }

        // Validate Items
        $(".item-name, .item-quantity").each(function () {
            if (!$(this).val().trim() || $(this).val() <= 0) {
                isValid = false;
                setFieldError($(this));
            } else {
                setFieldValid($(this));
            }
        });

        if (!isValid) {
            $("#error").text(errorMessage);
        } else {
            $("#error").text("");
            alert("Request submitted successfully!");
        }
    });
});

// Helper functions
function validateField(field) {
    if (!field.val().trim()) {
        setFieldError(field);
    } else {
        setFieldValid(field);
    }
}

function setFieldError(field) {
    field.css("border-color", "red");
}

function setFieldValid(field) {
    field.css("border-color", "green");
}
