var sidebar = document.getElementById('sidebar');
var opn = false;
var tgl = document.getElementById("toggleButton");
var isClass1 = false;
tgl.classList.add("class1");


var dropdownButtons = document.querySelectorAll('.dropdown-btn');
dropdownButtons.forEach(function(button) {
  button.addEventListener('click', function() {
    var dropdown = this.parentNode;
    var icon = this.querySelector('i');

    // Close all open dropdowns except the clicked one
    dropdownButtons.forEach(function(otherButton) {
      if (otherButton !== button) {
        var otherDropdown = otherButton.parentNode;
        otherDropdown.classList.remove('show');
        var otherIcon = otherButton.querySelector('i');
        otherIcon.classList.remove('fa-caret-up');
      }
    });

    // Toggle the clicked dropdown
    dropdown.classList.toggle('show');
    icon.classList.toggle('fa-caret-up');
  });
});

// Hide the dropdown content when the cursor hovers outside the dropdown container
/*
var dropdowns = document.querySelectorAll('.dropdown');
dropdowns.forEach(function(dropdown) {
  dropdown.addEventListener('mouseleave', function() {
    this.classList.remove('show');
  });
});
*/

function toggleSidebar() {
  sidebar.classList.toggle('open');
  opn = !opn;
  console.log(opn)
}


tgl.addEventListener("click", function() {
  if (isClass1) {
    tgl.innerHTML = '<i class="fa-solid fa-list"></i>';
    tgl.classList.remove("class2");
    tgl.classList.add("class1");
  } else {
    tgl.innerHTML = '<i class="fa-solid fa-xmark"></i>';
    tgl.classList.remove("class1");
    tgl.classList.add("class2");
  }
  isClass1 = !isClass1;
});

function downloadZip() {
    // Replace 'YOUR_PUBLIC_LINK' with the actual link to your zip file.
    var zipFileUrl = 'https://drive.google.com/drive/u/1/folders/1T7PNVPjMOQArZHmZbvn4AxCWtztIDei-';

    // Open the link in a new tab or window to initiate the download.
    window.open(zipFileUrl, '_blank');
}

