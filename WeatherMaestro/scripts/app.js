const cityForm = document.querySelector('form');
const card = document.querySelector('.card');
const details = document.querySelector('.details');
const time = document.querySelector('img.time');
const icon = document.querySelector('.icon img');
const forecast = new Forecast();

const updateUI = data => {
  console.log(data);

  const { cityDetails, weather } = data;

  //  update details template
  if (cityDetails.Country.ID != 'US') {
    details.innerHTML = `
  <h5 class="my-3">${cityDetails.EnglishName}, ${cityDetails.Country.EnglishName}</h5>
          <div class="my-3">${weather.WeatherText}</div>
          <div class="display-4 my-4">
            <span>${weather.Temperature.Imperial.Value}</span>
            <span>&deg;F</span>
          </div>
  `;
  } else {
    details.innerHTML = `
  <h5 class="my-3">${cityDetails.EnglishName}, ${cityDetails.AdministrativeArea.EnglishName}</h5>
          <div class="my-3">${weather.WeatherText}</div>
          <div class="display-4 my-4">
            <span>${weather.Temperature.Imperial.Value}</span>
            <span>&deg;F</span>
          </div>
  `;
  }

  //  update the night/day & icon images:
  const iconSource = `img/icons/${weather.WeatherIcon}.svg`;
  icon.setAttribute('src', iconSource);

  let timeSource = weather.IsDayTime ? 'img/day.svg' : 'img/night.svg';
  time.setAttribute('src', timeSource);

  if (card.classList.contains('d-none')) {
    card.classList.remove('d-none');
  }
};

cityForm.addEventListener('submit', e => {
  e.preventDefault();
  // get city value
  const city = cityForm.city.value.trim();
  cityForm.reset();
  //  update the UI with new city
  forecast
    .updateCity(city)
    .then(data => updateUI(data))
    .catch(err => console.log(err));

  localStorage.setItem('city', city);
});

if (localStorage.getItem('city')) {
  forecast
    .updateCity(localStorage.getItem('city'))
    .then(data => updateUI(data))
    .catch(err => console.log(err));
}
