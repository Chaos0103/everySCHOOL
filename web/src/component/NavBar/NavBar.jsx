import React from 'react';
import styles from './NavBar.module.css';
import { useNavigate } from 'react-router';

export default function NavBar() {
  const navigate = useNavigate();
  const token = sessionStorage.getItem('token');

  function clickLogout() {
    sessionStorage.clear();
    navigate('/login');
  }

  function handleLogoClick() {
    if (token) {
      navigate('/');
    } else {
      navigate('/login');
    }
  }

  return (
    <div className={styles.navbar}>
      <div className={styles.logo} onClick={handleLogoClick}>
        <img className={styles.logoImg1} src={process.env.PUBLIC_URL + '/assets/main/logo.png'} alt='' />
        <img className={styles.logoImg2} src={process.env.PUBLIC_URL + '/assets/main/logo_text.png'} alt='' />
      </div>
      <div>
        {token ? (
          <div className={styles.menu}>
            <p>알림</p>
            <p onClick={clickLogout}>로그아웃</p>
          </div>
        ) : null}
      </div>
    </div>
  );
}
