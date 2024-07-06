document.getElementById("print_id").addEventListener("click", () => {
            document.getElementById("main_header_id").style.marginLeft = "2px"
            document.getElementById("gate_entry_header_id").style.width = "100%";  
            document.getElementById("KCE_img").style.width = "60%";
            document.getElementById("nav_id").style.display = "none";
            document.getElementById("main_header_id").classList.add("col-lg-12");
            document.getElementById("gate_entry_header_img_id").style.width="90%";
            document.getElementById("gate_entry_login_details").style.display="none";
            document.getElementById("filter_details_id").style.display = "none";
            document.getElementById("Search_btn").innerHTML = "Report Details";
            document.getElementById("print_info_main_id").style.display="block";
            download();
        });
        document.addEventListener('DOMContentLoaded', function () {
            var currentDateElement = document.querySelector('#gate_entry_login_details > div:nth-child(1)');
 
            function updateDateTime() {
                var currentDateTime = new Date().toLocaleString(); 
                currentDateElement.textContent = currentDateTime;
            }
            updateDateTime();
            setInterval(updateDateTime, 1000);
        });
        function download() {
    let d = document.getElementById("invoice");
    console.log("aedhgwev");
    html2pdf()
        .from(d)
        .set({
            margin: [0.6, 0.6],
            filename: 'Entry_Details.pdf',
            pagebreak: { mode: 'avoid' },
            html2canvas: { scale: 2, dpi: 300 },
            jsPDF: { unit: 'in', format: 'letter', orientation: 'landscape' }
        })
        .toPdf()
        .get('pdf')
        .then(function (pdf) {
            var totalPages = pdf.internal.getNumberOfPages();
            var headerAdded = false;
            for (var i = 1; i <= totalPages; i++) {
                pdf.setPage(i);
                pdf.setFontSize(10);
                var dateTime = new Date().toLocaleString();
                pdf.text(dateTime, pdf.internal.pageSize.getWidth() - 0.5, pdf.internal.pageSize.getHeight() - 0.5, { align: 'right' });
               
            }
        })
        .save();
}

