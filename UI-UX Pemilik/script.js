function filter(){
    const filterButton = document.getElementById('filter-button');
    const filterTgl = document.getElementById('filterTgl');
    const cancelButton = document.getElementById('cancel-button');

    filterButton.addEventListener('click', () => {
        filterTgl.style.display = 'block';
        filterButton.style.display = 'none';
    });

    cancelButton.addEventListener('click', () => {
        filterTgl.style.display = 'none';
        filterButton.style.display = 'block';
    });
}

filter();