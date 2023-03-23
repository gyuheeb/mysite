import React from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/main/Main.scss';

export default function Main() {
    return (
        <MySiteLayout>
				<div className={styles.siteintroduction}>
					<img id="profile" src=""  />
					<h2>...</h2>
					<p>
						<br/><br/>
						<a href="">방명록</a>에 글 남기기<br/>
					</p>
				</div>
			
        </MySiteLayout>
    );
}