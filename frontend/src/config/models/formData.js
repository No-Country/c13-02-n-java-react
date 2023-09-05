import {useState} from 'react';

export const useFormData = () => {
    const [formData, setFormData] = useState({
        fullName: '',
        username: '',
        password: '',
        confirmPassword: '',
        businessName: '',
        phone: '',
        address: '',
    })

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData((prevData) => ({
            ...prevData,
            [name]: value,
        }))
    }

    return {formData, handleChange};
}