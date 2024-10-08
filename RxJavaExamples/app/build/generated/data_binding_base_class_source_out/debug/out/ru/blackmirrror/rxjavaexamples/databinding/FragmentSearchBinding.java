// Generated by view binder compiler. Do not edit!
package ru.blackmirrror.rxjavaexamples.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import ru.blackmirrror.rxjavaexamples.R;

public final class FragmentSearchBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextInputEditText etSearch;

  @NonNull
  public final RecyclerView rvMovies;

  @NonNull
  public final TextView tvNext;

  @NonNull
  public final TextView tvTimer;

  private FragmentSearchBinding(@NonNull LinearLayout rootView, @NonNull TextInputEditText etSearch,
      @NonNull RecyclerView rvMovies, @NonNull TextView tvNext, @NonNull TextView tvTimer) {
    this.rootView = rootView;
    this.etSearch = etSearch;
    this.rvMovies = rvMovies;
    this.tvNext = tvNext;
    this.tvTimer = tvTimer;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentSearchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentSearchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_search, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentSearchBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.et_search;
      TextInputEditText etSearch = ViewBindings.findChildViewById(rootView, id);
      if (etSearch == null) {
        break missingId;
      }

      id = R.id.rv_movies;
      RecyclerView rvMovies = ViewBindings.findChildViewById(rootView, id);
      if (rvMovies == null) {
        break missingId;
      }

      id = R.id.tv_next;
      TextView tvNext = ViewBindings.findChildViewById(rootView, id);
      if (tvNext == null) {
        break missingId;
      }

      id = R.id.tv_timer;
      TextView tvTimer = ViewBindings.findChildViewById(rootView, id);
      if (tvTimer == null) {
        break missingId;
      }

      return new FragmentSearchBinding((LinearLayout) rootView, etSearch, rvMovies, tvNext,
          tvTimer);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
