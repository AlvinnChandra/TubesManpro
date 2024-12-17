function filter(){
    const filterButton = document.getElementById('filter-button');
    const filterTgl = document.getElementById('filterTgl');
    const cancelButton = document.getElementById('cancel-button');
    const submitButton = document.getElementById('submit-button');

    filterButton.addEventListener('click', () => {
        filterTgl.style.display = 'block';
        filterButton.style.display = 'none';
    });

    cancelButton.addEventListener('click', () => {
        filterTgl.style.display = 'none';
        filterButton.style.display = 'block';
    });

    submitButton.addEventListener('click', () => {
        filterTgl.style.display = 'none';
        filterButton.style.display = 'block';
    });
}

const laporanTableBody = document.getElementById('laporan-table-body');
function loadLaporan() {
    fetch('/api/laporan/all')
        .then(response => response.json())
        .then(laporanList => {
            laporanList.sort((a, b) => a.id - b.id); // Sorting berdasarkan ID

            laporanTableBody.innerHTML = ''; // Clear table body
            laporanList.forEach(laporan => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${laporan.id}</td>
                    <td>${laporan.nama}</td>
                    <td>${laporan.merek}</td>
                    <td>${laporan.tanggal}</td>
                    <td>${laporan.waktuPakai}</td>
                    <td>${laporan.jamMulai}</td>
                    <td>${laporan.jamSelesai}</td>
                    <td>${laporan.tarif}</td>
                `;
                laporanTableBody.appendChild(row);
            });

        })
        .catch(error => {
            console.error('Gagal memuat data mesin:', error);
        });
}
document.getElementById('form-filter').addEventListener('submit', function (event) {
    event.preventDefault();

    const date1 = document.getElementById('date1').value;
    const date2 = document.getElementById('date2').value;

    if (!date1 || !date2) {
        alert('Silakan isi rentang tanggal.');
        return;
    }

    fetch(`/api/laporan/filter?dariTanggal=${date1}&sampaiTanggal=${date2}`)
        .then(response => response.json())
        .then(laporanList => {
            laporanTableBody.innerHTML = '';
            laporanList.forEach(laporan => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${laporan.id}</td>
                    <td>${laporan.nama}</td>
                    <td>${laporan.merek}</td>
                    <td>${laporan.tanggal}</td>
                    <td>${laporan.waktuPakai}</td>
                    <td>${laporan.jamMulai}</td>
                    <td>${laporan.jamSelesai}</td>
                    <td>${laporan.tarif}</td>
                `;
                laporanTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Gagal memuat data berdasarkan filter:', error));
});

// Merek terbanyak dipesan
document.getElementById('most-ordered-button').addEventListener('click', function () {
    fetch('/api/laporan/most-ordered-brand')
        .then(response => response.json())
        .then(laporanList => {
            laporanTableBody.innerHTML = '';
            laporanList.forEach(laporan => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${laporan.id}</td>
                    <td>${laporan.nama}</td>
                    <td>${laporan.merek}</td>
                    <td>${laporan.tanggal}</td>
                    <td>${laporan.waktuPakai}</td>
                    <td>${laporan.jamMulai}</td>
                    <td>${laporan.jamSelesai}</td>
                    <td>${laporan.tarif}</td>
                `;
                laporanTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Gagal memuat data merek terbanyak dipesan:', error));
});

// Merek paling sedikit dipesan
document.getElementById('least-ordered-button').addEventListener('click', function () {
    fetch('/api/laporan/least-ordered-brand')
        .then(response => response.json())
        .then(laporanList => {
            laporanTableBody.innerHTML = '';
            laporanList.forEach(laporan => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${laporan.id}</td>
                    <td>${laporan.nama}</td>
                    <td>${laporan.merek}</td>
                    <td>${laporan.tanggal}</td>
                    <td>${laporan.waktuPakai}</td>
                    <td>${laporan.jamMulai}</td>
                    <td>${laporan.jamSelesai}</td>
                    <td>${laporan.tarif}</td>
                `;
                laporanTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('Gagal memuat data merek paling sedikit dipesan:', error));
});

function loadTotalPendapatan(dariTanggal = null, sampaiTanggal = null) {
    let url = '/api/laporan/total-pendapatan';
    if (dariTanggal && sampaiTanggal) {
        url = `/api/laporan/total-pendapatan-filter?dariTanggal=${dariTanggal}&sampaiTanggal=${sampaiTanggal}`;
    }

    fetch(url)
        .then(response => response.json())
        .then(total => {
            document.getElementById('total-pendapatan').textContent = total;
        })
        .catch(error => console.error('Gagal memuat total pendapatan:', error));
}

// Load total pendapatan saat halaman dimuat
document.addEventListener('DOMContentLoaded', () => {
    loadTotalPendapatan();
});

// Panggil fungsi setelah filter diterapkan
document.getElementById('form-filter').addEventListener('submit', function (event) {
    event.preventDefault();

    const date1 = document.getElementById('date1').value;
    const date2 = document.getElementById('date2').value;

    if (!date1 || !date2) {
        alert('Silakan isi rentang tanggal.');
        return;
    }

    fetch(`/api/laporan/filter?dariTanggal=${date1}&sampaiTanggal=${date2}`)
        .then(response => response.json())
        .then(laporanList => {
            laporanTableBody.innerHTML = '';
            laporanList.forEach(laporan => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${laporan.id}</td>
                    <td>${laporan.nama}</td>
                    <td>${laporan.merek}</td>
                    <td>${laporan.tanggal}</td>
                    <td>${laporan.waktuPakai}</td>
                    <td>${laporan.jamMulai}</td>
                    <td>${laporan.jamSelesai}</td>
                    <td>${laporan.tarif}</td>
                `;
                laporanTableBody.appendChild(row);
            });

            // Load total pendapatan dengan filter
            loadTotalPendapatan(date1, date2);
        })
        .catch(error => console.error('Gagal memuat data berdasarkan filter:', error));
});


loadLaporan();

filter();