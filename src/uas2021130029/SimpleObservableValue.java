package uas2021130029;

import javafx.beans.value.ObservableValueBase;

public class SimpleObservableValue<T> extends ObservableValueBase<T> {
	
	private T value;

	public SimpleObservableValue(T value) {
		this.value = value;
	}
	
	@Override
	public T getValue() {
		return value;
	}
}
