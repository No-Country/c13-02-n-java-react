import React, { useRef, useState } from "react";
import { Button, Col, Container, Form, Image, Row } from "react-bootstrap";
import { BsEnvelopeFill } from "react-icons/bs";
import Imagenes from "../../../assets/imagenes.jsx";
import { formGroups } from "../../../config/Data/ArraysItems.js";

const Settings = () => {
	const [selectFile, setSelectFile] = useState(null);
	const fileInputRef = useRef(null);

	const handleFileChange = event => setSelectFile(event.target.files[0]);
	const handleUploadButtonClick = () => fileInputRef.current.click();

	return (
		<Container>
			{/* Imagen */}
			<Col xs={1} sm={2} md={12}>
				<Image
					src={selectFile ? URL.createObjectURL(selectFile) : Imagenes.perfil2}
					alt='Perfil'
					roundedCircle
				/>
				<input
					type='file'
					accept='image/*'
					onChange={handleFileChange}
					style={{ display: "none" }}
					ref={fileInputRef}
				/>
				<Button variant='info' onClick={handleUploadButtonClick}>
					Upload new
				</Button>{" "}
				<Button variant='secondary'>Remove profile</Button>
			</Col>
			<hr />
			<Col>
				<Form xs={1} md={12} className='g-4'>
					{formGroups.map((group, index) => (
						<Row md={2} key={index}>
							{group.map((field, fieldIndex) => (
								<Form.Group className='mb-3' controlId={`formGroup${fieldIndex}`} key={fieldIndex}>
									<Form.Label>{field.label}</Form.Label>
									<Form.Control type={field.type} placeholder={field.placeholder} />
								</Form.Group>
							))}
						</Row>
						))}
					{/* Boton */}
					<div className="d-flex justify-content-end">
						<Button variant='primary' type='submit'>
							Update Profile
						</Button>
					</div>
				</Form>
			</Col>
		</Container>
	);
};

export default Settings;
