import React, {useEffect} from 'react';
import MySiteLayout from "../../layout/MySiteLayout";
import styles from '../../assets/scss/component/user/User.scss';

export default function SignUp() {
    const join = async () => {
        try {
            const response = await fetch('/api/signup', {
                method: 'get',
                headers: {
                    'Accept': 'application/json'
                }
            });

            if (!response.ok) {
                throw new Error(`${response.status} ${response.statusText}`);
            }

            const json = await response.json();
            if (json.result !== 'success') {
                throw new Error(`${json.result} ${json.message}`);
            }

            setImageList(json.data);
        } catch (err) {
            console.error(err);
        }
    }
    useEffect(()=>{
        join();
    }, []);

    return (
        <MySiteLayout>
                <div className={styles.joinform} >
					<input type='hidden' name="a" value="join"/>

					<label class="block-label" for="name">이름</label><br/>
					<input id="name" name="name" type="text" value=""/><br/>

					<label class="block-label" for="email">이메일</label><br/>
					<input id="email" name="email" type="text" value=""/>
					<input type="button" value="중복체크"/><br/>
					
					<label class="block-label">패스워드</label><br/>
					<input name="password" type="password" value=""/><br/>
					
					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female" checked="checked"/>
						<label>남</label> <input type="radio" name="gender" value="male"/>
					</fieldset>
					
					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y"/>
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>
					
					<input type="submit" value="가입하기"/>
				</div>	
         
        </MySiteLayout>
    );
}