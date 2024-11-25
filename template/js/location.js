document.addEventListener("DOMContentLoaded", () => {
    const provinceSelect = document.getElementById("city");
    const districtSelect = document.getElementById("district");
    const wardSelect = document.getElementById("ward");

    // Fetch and populate provinces
    fetch("http://localhost:8080/api/provinces")
        .then(response => response.json())
        .then(provinces => {
            provinces.forEach(province => {
                const option = document.createElement("option");
                option.value = province.code;
                option.textContent = province.name;
                provinceSelect.appendChild(option);
            });
        })
        .catch(error => console.error("Error fetching provinces:", error));

    // Fetch districts based on selected province
    provinceSelect.addEventListener("change", () => {
        const provinceCode = provinceSelect.value;
        districtSelect.innerHTML = "<option value=''>Select District</option>";
        wardSelect.innerHTML = "<option value=''>Select Ward</option>";

        if (provinceCode) {
            fetch(`http://localhost:8080/api/provinces/${provinceCode}/districts`)
                .then(response => response.json())
                .then(districts => {
                    districts.forEach(district => {
                        const option = document.createElement("option");
                        option.value = district.code;
                        option.textContent = district.name;
                        districtSelect.appendChild(option);
                    });
                })
                .catch(error => console.error("Error fetching districts:", error));
        }
    });

    // Fetch wards based on selected district
    districtSelect.addEventListener("change", () => {
        const districtCode = districtSelect.value;
        wardSelect.innerHTML = "<option value=''>Select Ward</option>";

        if (districtCode) {
            fetch(`http://localhost:8080/api/districts/${districtCode}/wards`)
                .then(response => response.json())
                .then(wards => {
                    wards.forEach(ward => {
                        const option = document.createElement("option");
                        option.value = ward.code;
                        option.textContent = ward.name;
                        wardSelect.appendChild(option);
                    });
                })
                .catch(error => console.error("Error fetching wards:", error));
        }
    });
});
