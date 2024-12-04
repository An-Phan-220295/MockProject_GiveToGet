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

    // Form submission
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
        let items = [];
        $(".item-name").each(function (index) {
            const name = $(this).val().trim();
            const quantity = $(".item-quantity").eq(index).val().trim();

            if (!name || !quantity || quantity <= 0) {
                isValid = false;
                setFieldError($(this));
                setFieldError($(".item-quantity").eq(index));
            } else {
                setFieldValid($(this));
                setFieldValid($(".item-quantity").eq(index));
                items.push({ name: name, quantity: quantity });
            }
        });

        if (!isValid) {
            $("#error").text(errorMessage);
            return;
        }

        // Collect form data
        let formData = new FormData();
        formData.append("title", title.val());
        formData.append("description", description.val());
        formData.append("image", imageUpload[0].files[0]);

        items.forEach((item, index) => {
            formData.append(`itemNames[${index}]`, item.name);
            formData.append(`quantities[${index}]`, item.quantity);
        });

        // AJAX POST request
        $.ajax({
            url: "http://localhost:8080/api/v1/givenrequest/create",
            type: "POST",
            data: formData,
            processData: false, // Prevent jQuery from automatically transforming the data into a query string
            contentType: false, // Use FormData's default content type
            success: function (response) {
                alert("Request submitted successfully!");
                console.log(response);
                $("#create-request-form")[0].reset(); // Reset form fields
                $("#items-container").empty(); // Clear added items
            },
            error: function (xhr, status, error) {
                alert("Failed to submit the request. Please try again.");
                console.error("Error:", error);
                console.error("Response:", xhr.responseText);
            },
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
});
